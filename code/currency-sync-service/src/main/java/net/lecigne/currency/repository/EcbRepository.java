package net.lecigne.currency.repository;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.experimental.ExtensionMethod;
import lombok.extern.slf4j.Slf4j;
import net.lecigne.currency.config.Configuration;
import net.lecigne.currency.domain.DailyExchangeRate;
import net.lecigne.currency.dto.EcbDataDto;
import net.lecigne.currency.dto.MarketDayDto;
import net.lecigne.currency.extensions.Extensions;
import net.lecigne.currency.mapper.EcbMapper;
import net.lecigne.currency.service.EcbClient;
import net.lecigne.currency.service.EcbService;
import net.lecigne.currency.service.EcbServiceRetrofit;

@RequiredArgsConstructor
@Slf4j
@ExtensionMethod({Extensions.class})
public class EcbRepository {

  private final EcbService service;
  private final DataSource dataSource;

  public static EcbRepository create(Configuration.Data dataConfig) {
    EcbServiceRetrofit ecbServiceRetrofit = new EcbServiceRetrofit(EcbClient.create(dataConfig.getEcbUrl()));
    var hikariConfig = new HikariConfig();
    // rewriteBatchedStatements=true speeds up insertion dramatically on initial full sync
    // see https://stackoverflow.com/a/24026539
    hikariConfig.setJdbcUrl(dataConfig.getDbUrl() + "?enabledTLSProtocols=TLSv1.2&rewriteBatchedStatements=true");
    hikariConfig.setUsername(dataConfig.getDbUser());
    hikariConfig.setPassword(dataConfig.getDbPassword());
    hikariConfig.setMinimumIdle(1);
    hikariConfig.setMaximumPoolSize(10);
    hikariConfig.setIdleTimeout(Duration.ofSeconds(30).toMillis());
    HikariDataSource hikariDataSource = new HikariDataSource(hikariConfig);
    return new EcbRepository(ecbServiceRetrofit, hikariDataSource);
  }

  public Set<DailyExchangeRate> getRatesFrom(LocalDate fromDate) throws Exception {
    log.info("Asking for rates from {}", fromDate);
    List<Supplier<EcbDataDto>> suppliers = List.of(
        service::getDailyRates,
        service::get90DaysRates,
        service::getFullRates
    );
    for (Supplier<EcbDataDto> supplier : suppliers) {
      EcbDataDto rates = supplier.get();
      LocalDate oldestDateInData = getOldestDate(rates).orElseThrow();
      if (fromDate.isEqualOrAfter(oldestDateInData)) {
        return rates.getMarketDays().stream()
            .filter(marketDay -> marketDay.getDate().isEqualOrAfter(fromDate))
            .flatMap(marketDayDto -> EcbMapper.map(marketDayDto).stream())
            .collect(Collectors.toSet());
      } else {
        log.warn("{} not included in fetched history, which starts from {} - extending search...", fromDate,
            oldestDateInData);
      }
    }
    // Can't extend search more
    throw new Exception();
  }

  private Optional<LocalDate> getOldestDate(EcbDataDto ecbData) {
    return ecbData.getMarketDays().stream()
        .map(MarketDayDto::getDate)
        .min(LocalDate::compareTo);
  }

  public void insertRates(Set<DailyExchangeRate> rates) {
    var sql = "INSERT INTO exchange_rate(currency, date, rate) VALUES (?, ?, ?)";
    try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
      for (DailyExchangeRate rate : rates) {
        statement.setString(1, rate.getCurrency());
        statement.setDate(2, Date.valueOf(rate.getDate()));
        statement.setBigDecimal(3, rate.getRate());
        statement.addBatch();
      }
      statement.executeBatch();
    } catch (SQLException e) {
      log.error("Error", e);
    }
  }

  public Optional<LocalDate> getNextFromDate() {
    log.info("Fetch next date to sync from...");
    var sql = "SELECT date FROM exchange_rate ORDER BY date DESC LIMIT 1";
    try (Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql)) {
      if (resultSet.next()) {
        LocalDate nextDate = resultSet.getDate(1)
            .toLocalDate()
            .plusDays(1);
        log.info("Next date to sync from: {}", nextDate);
        return Optional.of(nextDate);
      }
    } catch (SQLException e) {
      log.error("Error");
    }
    return Optional.empty();
  }

}
