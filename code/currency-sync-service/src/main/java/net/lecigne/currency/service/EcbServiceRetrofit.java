package net.lecigne.currency.service;

import static java.time.temporal.ChronoUnit.SECONDS;

import dev.failsafe.RetryPolicy;
import dev.failsafe.retrofit.FailsafeCall;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.lecigne.currency.dto.EcbDataDto;
import retrofit2.Call;
import retrofit2.Response;

@RequiredArgsConstructor
@Slf4j
public class EcbServiceRetrofit implements EcbService {

  private final EcbClient ecbClient;

  @Override
  public EcbDataDto getDailyRates() {
    log.info("Fetching daily rates");
    Call<EcbDataDto> callForDailyRates = ecbClient.getDailyRates();
    return callWithRetry(callForDailyRates);
  }

  @Override
  public EcbDataDto get90DaysRates() {
    log.info("Fetching 90-day history of rates");
    Call<EcbDataDto> callFor90DaysRates = ecbClient.get90DaysRates();
    return callWithRetry(callFor90DaysRates);
  }

  @Override
  public EcbDataDto getFullRates() {
    log.info("Fetching full history of rates");
    Call<EcbDataDto> callForFullRates = ecbClient.getFullRates();
    return callWithRetry(callForFullRates);
  }

  private EcbDataDto callWithRetry(Call<EcbDataDto> call) {
    RetryPolicy<Response<EcbDataDto>> retryPolicy = RetryPolicy.<Response<EcbDataDto>>builder()
        .withMaxRetries(3)
        .withDelay(1, 30, SECONDS)
        .build();
    FailsafeCall<EcbDataDto> failsafeCall = FailsafeCall.with(retryPolicy).compose(call);
    try {
      return failsafeCall.execute().body();
    } catch (IOException e) {
      log.error("Error fetching rates...");
      throw new RuntimeException(e);
    }
  }
}
