package net.lecigne.codingkatas.springwebmvcresttemplate;

import net.lecigne.codingkatas.springwebmvcresttemplate.animalfact.AnimalFact;
import net.lecigne.codingkatas.springwebmvcresttemplate.animalfact.AnimalFactClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static net.lecigne.codingkatas.springwebmvcresttemplate.animalfact.Animal.CAT;
import static net.lecigne.codingkatas.springwebmvcresttemplate.animalfact.Animal.DOG;
import static net.lecigne.codingkatas.springwebmvcresttemplate.animalfact.Animal.HORSE;

@SpringBootApplication
public class Application implements CommandLineRunner {
    private final AnimalFactClient animalFactClient;

    @Autowired
    public Application(AnimalFactClient animalFactClient) {
        this.animalFactClient = animalFactClient;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args).close();
    }

    @Override
    @SuppressWarnings("squid:S106") // System.out.println is fine
    public void run(String... args) {
        AnimalFact dogFact = animalFactClient.getRandomFact(DOG);
        AnimalFact catFact = animalFactClient.getRandomFact(CAT);
        AnimalFact horseFact = animalFactClient.getRandomQuoteWithExchange(HORSE);
        System.out.println(dogFact.toString());
        System.out.println(catFact.toString());
        System.out.println(horseFact.toString());
    }
}
