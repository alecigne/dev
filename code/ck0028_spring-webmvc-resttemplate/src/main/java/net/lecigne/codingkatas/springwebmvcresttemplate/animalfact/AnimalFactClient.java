package net.lecigne.codingkatas.springwebmvcresttemplate.animalfact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AnimalFactClient {

    @Value("${animalfact.url}")
    private String animalFactUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public AnimalFactClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public AnimalFact getRandomFact(Animal animal) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(animalFactUrl)
                .queryParam("animal_type", animal.getType());
        return restTemplate.getForObject(builder.toUriString(), AnimalFact.class);
    }

    public AnimalFact getRandomQuoteWithExchange(Animal animal) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl(animalFactUrl)
                .queryParam("animal_type", animal.getType());
        return restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                new HttpEntity<>(new AnimalFact()), AnimalFact.class).getBody();
    }
}
