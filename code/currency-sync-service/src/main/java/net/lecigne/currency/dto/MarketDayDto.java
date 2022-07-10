package net.lecigne.currency.dto;

import static lombok.AccessLevel.PRIVATE;
import static net.lecigne.currency.EcbConstants.ECB_CUBE;
import static net.lecigne.currency.EcbConstants.ECB_TIME;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true, access = PRIVATE)
@AllArgsConstructor
@Builder
public class MarketDayDto {
  @JacksonXmlProperty(localName = ECB_TIME, isAttribute = true)
  LocalDate date;

  @JacksonXmlProperty(localName = ECB_CUBE)
  @JacksonXmlElementWrapper(useWrapping = false)
  List<ExchangeRateDto> exchangeRates;
}
