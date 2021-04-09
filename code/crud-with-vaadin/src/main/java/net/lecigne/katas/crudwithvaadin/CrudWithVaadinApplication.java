package net.lecigne.katas.crudwithvaadin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudWithVaadinApplication {

    private static final Logger log = LoggerFactory.getLogger(CrudWithVaadinApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CrudWithVaadinApplication.class);
    }

    @Bean
    @SuppressWarnings("java:S1192") // a few string duplications are ok
    public CommandLineRunner loadData(CustomerRepository repository) {
        return args -> {
            // save a couple of customers
            repository.save(new Customer("Jack", "Bauer"));
            repository.save(new Customer("Chloe", "O'Brian"));
            repository.save(new Customer("Kim", "Bauer"));
            repository.save(new Customer("David", "Palmer"));
            repository.save(new Customer("Michelle", "Dessler"));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            repository.findAll().forEach(customer -> log.info(customer.toString()));
            log.info("");

            // fetch an individual customer by ID
            repository.findById(1L).ifPresent(c -> {
                log.info("Customer found with findOne(1L):");
                log.info("--------------------------------");
                log.info(c.toString());
                log.info("");
            });

            // fetch customers by last name
            log.info("Customer found with findByLastNameStartsWithIgnoreCase('Bauer'):");
            log.info("--------------------------------------------");
            repository.findByLastNameStartsWithIgnoreCase("Bauer").forEach(customer -> log.info(customer.toString()));
            log.info("");
        };
    }

}
