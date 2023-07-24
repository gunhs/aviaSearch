package ru.sharanov.aviasearch;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.sharanov.aviasearch.service.FlightsService;

@SpringBootApplication
public class AviaSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(AviaSearchApplication.class, args);
    }

    @Bean
    ApplicationRunner applicationRunner(FlightsService flightsService) {
        return args -> flightsService.callMainMenu();
    }
}