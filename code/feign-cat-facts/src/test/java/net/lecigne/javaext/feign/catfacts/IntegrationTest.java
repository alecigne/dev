package net.lecigne.javaext.feign.catfacts;

import static org.assertj.core.api.Assertions.assertThat;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import java.util.List;
import net.lecigne.javaext.feign.catfacts.client.FeignClient;
import net.lecigne.javaext.feign.catfacts.model.AnimalFact;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("The application")
class IntegrationTest {

  private static final FeignClient CLIENT = Feign.builder()
      .client(new OkHttpClient())
      .encoder(new GsonEncoder())
      .decoder(new GsonDecoder())
      .target(FeignClient.class, "https://cat-fact.herokuapp.com");

  @Test
  void should_retrieve_cat_facts_from_third_party_api() {
    // When
    List<AnimalFact> facts = CLIENT.getFacts("dog", 5);

    // Then
    assertThat(facts)
        .hasSize(5)
        .extracting(AnimalFact::getAnimalType)
        .containsOnly("dog");
  }

}
