package net.lecigne.currency.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.val;
import net.lecigne.currency.domain.DailyExchangeRate;

@RequiredArgsConstructor
public class TestRepository {

  private final DataSource testDataSource;

  public Set<DailyExchangeRate> readAllRates() throws IOException {
    var sql = "SELECT * FROM exchange_rate";
    Set<DailyExchangeRate> results = new HashSet<>();
    try (Connection connection = testDataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery()) {
      while (resultSet.next()) {
        val currency = resultSet.getString(1);
        val date = resultSet.getDate(2);
        val rate = resultSet.getBigDecimal(3);
        val exchangeRate = DailyExchangeRate.builder()
            .currency(currency)
            .date(date.toLocalDate())
            .rate(rate).build();
        results.add(exchangeRate);
      }

    } catch (Exception e) {
      throw new IOException();
    }
    return results;
  }

}
