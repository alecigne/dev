package net.lecigne.currency.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.sql.DataSource;
import net.lecigne.currency.domain.DailyExchangeRate;
import net.lecigne.currency.dto.EcbDataDto;
import net.lecigne.currency.dto.ExchangeRateDto;
import net.lecigne.currency.dto.MarketDayDto;
import net.lecigne.currency.service.EcbService;
import net.lecigne.currency.service.EcbServiceRetrofit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EcbRepositoryTest {

  public static final String PINNED_CURRENCY = "USD";
  public static final String PINNED_RATE = "1.0163";
  private EcbService mockService;
  private EcbRepository repository;

  @BeforeEach
  void setUp() {
    mockService = mock(EcbServiceRetrofit.class);
    repository = new EcbRepository(mockService, mock(DataSource.class));
  }

  @Test
  void shouldReturnNothingIfFromDateAfterLastAvailableDate() throws Exception {
    // Given
    var fromDate = "2022-07-10";
    EcbDataDto ecbData = buildHistory("2022-07-09");
    given(mockService.getDailyRates()).willReturn(ecbData);

    // When
    Set<DailyExchangeRate> exchangeRates = repository.getRatesFrom(LocalDate.parse(fromDate));

    // Then
    assertThat(exchangeRates).isEmpty();
  }

  @Test
  void shouldReturnNewExchangeRate() throws Exception {
    // Given
    var fromDate = "2022-07-10";
    var ecbData = buildHistory(fromDate);
    given(mockService.getDailyRates()).willReturn(ecbData);

    DailyExchangeRate expected = DailyExchangeRate.builder()
        .date(LocalDate.parse(fromDate))
        .currency(PINNED_CURRENCY)
        .rate(new BigDecimal(PINNED_RATE))
        .build();

    // When
    Set<DailyExchangeRate> exchangeRates = repository.getRatesFrom(LocalDate.parse(fromDate));

    // Then
    assertThat(exchangeRates)
        .hasSize(1)
        .containsExactly(expected);
  }

  @Test
  void shouldHandleMissingDaysWithinA90DaysWindow() throws Exception {
    // Given
    var fromDate = "2022-07-01";
    var dailyEcbData = buildHistory("2022-07-10");
    given(mockService.getDailyRates()).willReturn(dailyEcbData);

    EcbDataDto ninetyDaysEcbData = buildHistory("2022-06-30", "2022-07-01");
    given(mockService.get90DaysRates()).willReturn(ninetyDaysEcbData);

    DailyExchangeRate expected = DailyExchangeRate.builder()
        .date(LocalDate.parse(fromDate))
        .currency(PINNED_CURRENCY)
        .rate(new BigDecimal(PINNED_RATE))
        .build();

    // When
    Set<DailyExchangeRate> exchangeRates = repository.getRatesFrom(LocalDate.parse(fromDate));

    // Then
    assertThat(exchangeRates)
        .hasSize(1)
        .containsExactly(expected);
  }

  @Test
  void shouldHandleMissingDaysWithinAFullHistoryWindow() throws Exception {
    // Given
    var fromDate = "2021-07-01";
    EcbDataDto dailyEcbData = buildHistory("2022-07-10");
    given(mockService.getDailyRates()).willReturn(dailyEcbData);

    EcbDataDto ninetyDaysEcbData = buildHistory("2022-06-30", "2022-07-01");
    given(mockService.get90DaysRates()).willReturn(ninetyDaysEcbData);

    EcbDataDto fullHistoryEcbData = buildHistory("2021-06-30", "2021-07-01", "2021-12-10");
    given(mockService.getFullRates()).willReturn(fullHistoryEcbData);

    Set<DailyExchangeRate> expected = Set.of(
        DailyExchangeRate.builder()
            .date(LocalDate.parse("2021-12-10"))
            .currency(PINNED_CURRENCY)
            .rate(new BigDecimal(PINNED_RATE))
            .build(),
        DailyExchangeRate.builder()
            .date(LocalDate.parse("2021-07-01"))
            .currency(PINNED_CURRENCY)
            .rate(new BigDecimal(PINNED_RATE))
            .build());

    // When
    Set<DailyExchangeRate> exchangeRates = repository.getRatesFrom(LocalDate.parse(fromDate));

    // Then
    assertThat(exchangeRates)
        .hasSize(2)
        .isEqualTo(expected);
  }

  @Test
  void shouldThrowExceptionWhenFromDateOlderThanFullHistory() {
    // Given
    var fromDate = LocalDate.of(1995, 1, 1);
    EcbDataDto dailyEcbData = buildHistory("2022-07-10");
    given(mockService.getDailyRates()).willReturn(dailyEcbData);

    EcbDataDto ninetyDaysEcbData = buildHistory("2022-06-30", "2022-07-01");
    given(mockService.get90DaysRates()).willReturn(ninetyDaysEcbData);

    EcbDataDto fullHistoryEcbData = buildHistory("1999-01-01", "2021-07-01", "2021-12-10");
    given(mockService.getFullRates()).willReturn(fullHistoryEcbData);

    // When - Then
    assertThatThrownBy(() -> repository.getRatesFrom(fromDate)).isInstanceOf(Exception.class);
  }

  private EcbDataDto buildHistory(String... datesInHistory) {
    List<MarketDayDto> marketDayDtos = Arrays.stream(datesInHistory).map(date -> MarketDayDto.builder()
        .date(LocalDate.parse(date))
        .exchangeRates(Collections.singletonList(ExchangeRateDto.builder()
            .currency(PINNED_CURRENCY)
            .rate(PINNED_RATE)
            .build()))
        .build()).toList();
    return EcbDataDto.builder().marketDays(marketDayDtos).build();
  }

}
