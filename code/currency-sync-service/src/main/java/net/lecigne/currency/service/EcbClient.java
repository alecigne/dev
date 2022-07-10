package net.lecigne.currency.service;

import static net.lecigne.currency.EcbConstants.ECB_90_DAYS_RATES_ENDPOINT;
import static net.lecigne.currency.EcbConstants.ECB_DAILY_RATES_ENDPOINT;
import static net.lecigne.currency.EcbConstants.ECB_FULL_RATES_ENDPOINT;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.val;
import net.lecigne.currency.dto.EcbDataDto;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.http.GET;

public interface EcbClient {
  static EcbClient create(String baseUrl) {
    val mapper = new XmlMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .registerModule(new JavaTimeModule());
    return new Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(JacksonConverterFactory.create(mapper))
        .client(new OkHttpClient.Builder().build())
        .build()
        .create(EcbClient.class);
  }

  @GET(ECB_DAILY_RATES_ENDPOINT)
  Call<EcbDataDto> getDailyRates();

  @GET(ECB_90_DAYS_RATES_ENDPOINT)
  Call<EcbDataDto> get90DaysRates();

  @GET(ECB_FULL_RATES_ENDPOINT)
  Call<EcbDataDto> getFullRates();

}
