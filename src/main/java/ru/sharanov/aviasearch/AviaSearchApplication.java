package ru.sharanov.aviasearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AviaSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(AviaSearchApplication.class, args);
        System.out.println("Сервис поиска авиабилетов\n\n");
    }
}