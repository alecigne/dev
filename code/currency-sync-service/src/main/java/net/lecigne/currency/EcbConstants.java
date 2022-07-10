package net.lecigne.currency;

import lombok.experimental.UtilityClass;

@UtilityClass
public class EcbConstants {
  public static final String ECB_GESMES = "gesmes";
  public static final String ECB_ENVELOPE = "Envelope";
  public static final String ECB_CUBE = "Cube";
  public static final String ECB_TIME = "time";
  public static final String ECB_CURRENCY = "currency";
  public static final String ECB_RATE = "rate";

  public static final String ECB_DAILY_RATES_ENDPOINT = "/stats/eurofxref/eurofxref-daily.xml";
  public static final String ECB_90_DAYS_RATES_ENDPOINT = "/stats/eurofxref/eurofxref-hist-90d.xml";
  public static final String ECB_FULL_RATES_ENDPOINT = "/stats/eurofxref/eurofxref-hist.xml";
}
