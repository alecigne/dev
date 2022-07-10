package net.lecigne.currency.dto;

import static lombok.AccessLevel.PRIVATE;
import static net.lecigne.currency.EcbConstants.ECB_CUBE;
import static net.lecigne.currency.EcbConstants.ECB_ENVELOPE;
import static net.lecigne.currency.EcbConstants.ECB_GESMES;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Value;

@Value
@NoArgsConstructor(force = true, access = PRIVATE)
@AllArgsConstructor
@Builder
@JacksonXmlRootElement(namespace = ECB_GESMES, localName = ECB_ENVELOPE)
public class EcbDataDto {
  @JacksonXmlProperty(localName = ECB_CUBE)
  List<MarketDayDto> marketDays;
}
