package net.lecigne.javaext.feign.catfacts.client;

import feign.Param;
import feign.RequestLine;
import net.lecigne.javaext.feign.catfacts.model.AnimalFact;

import java.util.List;

public interface FeignClient {

    @RequestLine("GET /facts/random?animal_type={animal}&amount={amount}")
    List<AnimalFact> getFacts(@Param("animal") String animal, @Param("amount") int amount);

}
