package net.lecigne.currency.service;

import static net.lecigne.currency.EcbConstants.ECB_90_DAYS_RATES_ENDPOINT;
import static net.lecigne.currency.EcbConstants.ECB_DAILY_RATES_ENDPOINT;
import static net.lecigne.currency.EcbConstants.ECB_FULL_RATES_ENDPOINT;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.zip.ZipInputStream;
import lombok.experimental.ExtensionMethod;
import lombok.val;
import net.lecigne.currency.dto.EcbDataDto;
import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@ExtensionMethod(EcbServiceRetrofitIT.Extensions.class)
class EcbServiceRetrofitIT {

  static MockWebServer mockWebServer;
  static EcbService ecbService;

  @BeforeAll
  static void beforeAll() throws IOException {
    mockWebServer = new MockWebServer();
    mockWebServer.start();
    mockWebServer.setDispatcher(getDispatcher());
    EcbClient client = EcbClient.create(mockWebServer.url("/").toString());
    ecbService = new EcbServiceRetrofit(client);
  }

  @AfterAll
  static void afterAll() throws IOException {
    mockWebServer.shutdown();
  }

  private static Dispatcher getDispatcher() {
    return new Dispatcher() {
      @Override
      public MockResponse dispatch(RecordedRequest recordedRequest) {
        return switch (recordedRequest.getPath()) {
          case ECB_DAILY_RATES_ENDPOINT -> new MockResponse()
              .setResponseCode(200)
              .setBody(getDummyXml("/test-data/daily_rates.xml"));
          case ECB_90_DAYS_RATES_ENDPOINT -> new MockResponse()
              .setResponseCode(200)
              .setBody(getDummyXml("/test-data/90_days_rates.xml"));
          case ECB_FULL_RATES_ENDPOINT -> new MockResponse()
              .setResponseCode(200)
              .setBody(getDummyXmlFromZip("/test-data/full_rates.zip"));
          default -> new MockResponse().setResponseCode(404);
        };
      }
    };
  }

  private static String getDummyXml(String path) {
    try (InputStream resourceAsStream = EcbServiceRetrofitIT.class.getResourceAsStream(path)) {
      return new String(resourceAsStream.readAllBytes());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @SuppressWarnings("SameParameterValue")
  private static String getDummyXmlFromZip(String path) {
    try (InputStream resourceAsStream = EcbServiceRetrofitIT.class.getResourceAsStream(path);
        ZipInputStream zipInputStream = new ZipInputStream(resourceAsStream)) {
      zipInputStream.getNextEntry();
      return new String(zipInputStream.readAllBytes());
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  void shouldGetDailyRates() throws InterruptedException {
    // When
    EcbDataDto result = ecbService.getDailyRates();

    // Then
    val request = mockWebServer.takeRequest();
    assertThat(request.getPath()).isEqualTo(ECB_DAILY_RATES_ENDPOINT);
    assertThat(result).isNotNull();
    assertThat(result.getMarketDays()).isNotNull().hasSize(1);
    assertThat(result.getMarketDays().getLast().getDate()).isEqualTo(LocalDate.of(2022, 7, 8));
  }

  @Test
  void shouldGet90DaysRates() throws InterruptedException {
    // When
    EcbDataDto result = ecbService.get90DaysRates();

    // Then
    val request = mockWebServer.takeRequest();
    assertThat(request.getPath()).isEqualTo(ECB_90_DAYS_RATES_ENDPOINT);
    assertThat(result).isNotNull();
    assertThat(result.getMarketDays()).isNotNull().hasSize(63);
    assertThat(result.getMarketDays().getLast().getDate()).isEqualTo(LocalDate.of(2022, 4, 11));
  }

  @Test
  void shouldGetFullRates() throws InterruptedException {
    // When
    EcbDataDto result = ecbService.getFullRates();

    // Then
    val request = mockWebServer.takeRequest();
    assertThat(request.getPath()).isEqualTo(ECB_FULL_RATES_ENDPOINT);
    assertThat(result).isNotNull();
    assertThat(result.getMarketDays()).isNotNull().hasSize(6023);
    assertThat(result.getMarketDays().getLast().getDate()).isEqualTo(LocalDate.of(1999, 1, 4));
  }

  @SuppressWarnings("unused")
  public static class Extensions {
    public static <T> T getLast(List<T> list) {
      return list != null && !list.isEmpty() ? list.get(list.size() - 1) : null;
    }
  }

}
