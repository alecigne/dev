package net.lecigne.javaext.feign.catfacts;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import net.lecigne.javaext.feign.catfacts.client.FeignClient;
import net.lecigne.javaext.feign.catfacts.model.AnimalFact;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IntegrationTest {

    private static FeignClient client;

    @BeforeAll
    public static void setUp() {
        client = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(FeignClient.class, "https://cat-fact.herokuapp.com");
    }

    @Test
    void simpleTest() {
        List<AnimalFact> facts = client.getFacts("dog", 5);
        facts.forEach(System.out::println);
        assertEquals(5, facts.size());
        assertTrue(facts.stream().allMatch(f -> "dog".equals(f.getAnimalType())));
    }

}
