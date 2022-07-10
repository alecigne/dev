package net.lecigne.currency.mapper;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import net.lecigne.currency.domain.DailyExchangeRate;
import net.lecigne.currency.dto.MarketDayDto;

@UtilityClass
public class EcbMapper {

  public Set<DailyExchangeRate> map(MarketDayDto marketDayDto) {
    return marketDayDto.getExchangeRates().stream()
        .map(rate -> DailyExchangeRate.builder()
            .currency(rate.getCurrency())
            .date(marketDayDto.getDate())
            .rate(new BigDecimal(rate.getRate()))
            .build())
        .collect(Collectors.toSet());
  }

}
