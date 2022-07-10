package net.lecigne.currency.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Configuration {

  public static final String ROOT_CONFIG = "config";

  private Business business;
  private Data data;

  @NoArgsConstructor
  @Getter
  @Setter
  public static class Business {
    private String fullSyncFrom;
  }

  @NoArgsConstructor
  @Getter
  @Setter
  public static class Data {
    private String ecbUrl;
    private String dbUrl;
    private String dbUser;
    private String dbPassword;
  }

}
