package net.lecigne.catfactsspringboot.client;

import feign.Param;
import feign.RequestLine;
import net.lecigne.catfactsspringboot.model.AnimalFact;

import java.util.List;

public interface AnimalFactsClient {
    @RequestLine("GET /facts/random?animal_type={animal}&amount={amount}")
    List<AnimalFact> getFacts(@Param("animal") String animal, @Param("amount") int amount);
}
