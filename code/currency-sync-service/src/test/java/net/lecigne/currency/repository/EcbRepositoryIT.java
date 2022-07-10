package net.lecigne.currency.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import lombok.val;
import net.lecigne.currency.domain.DailyExchangeRate;
import net.lecigne.currency.service.EcbService;
import net.lecigne.currency.utils.TestRepository;
import org.flywaydb.core.Flyway;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.internal.stubbing.answers.ThrowsException;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
class EcbRepositoryIT {

  @Rule
  public static MySQLContainer<?> mysql = new MySQLContainer<>("mysql");

  private static EcbRepository repository;
  private static TestRepository testRepository;

  @BeforeAll
  static void beforeAll() {
    mysql.start();
    val hikariConfig = new HikariConfig();
    hikariConfig.setJdbcUrl(mysql.getJdbcUrl());
    hikariConfig.setUsername(mysql.getUsername());
    hikariConfig.setPassword(mysql.getPassword());
    val hikariDataSource = new HikariDataSource(hikariConfig);
    EcbService mockService = mock(EcbService.class, new ThrowsException(new IllegalArgumentException()));
    repository = new EcbRepository(mockService, hikariDataSource);
    testRepository = new TestRepository(hikariDataSource);
    Flyway flyway = Flyway.configure().dataSource(hikariDataSource).load();
    flyway.migrate();
  }

  @Test
  void shouldInsertRates() throws IOException {
    // Given
    val date = LocalDate.of(2022, 7, 10);
    val currency = "USD";
    val rate = DailyExchangeRate.builder()
        .date(date)
        .currency(currency)
        .rate(new BigDecimal("1.2"))
        .build();

    val expectedRate = DailyExchangeRate.builder()
        .date(date)
        .currency(currency)
        .rate(new BigDecimal("1.2000")) // rate is stored as DECIMAL(12, 4)
        .build();

    // When
    repository.insertRates(Set.of(rate));

    // Then
    Set<DailyExchangeRate> rates = testRepository.readAllRates();
    assertThat(rates)
        .hasSize(1)
        .containsExactly(expectedRate);
  }

}
