package net.lecigne.currency.dto;

import static lombok.AccessLevel.PRIVATE;
import static net.lecigne.currency.EcbConstants.ECB_CURRENCY;
import static net.lecigne.currency.EcbConstants.ECB_RATE;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true, access = PRIVATE)
@AllArgsConstructor
@Builder
public class ExchangeRateDto {
  @JacksonXmlProperty(localName = ECB_CURRENCY, isAttribute = true)
  String currency;

  @JacksonXmlProperty(localName = ECB_RATE, isAttribute = true)
  String rate;
}
