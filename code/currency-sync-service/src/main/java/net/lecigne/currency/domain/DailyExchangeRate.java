package net.lecigne.currency.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class DailyExchangeRate {
  String currency;
  LocalDate date;
  BigDecimal rate;
}
