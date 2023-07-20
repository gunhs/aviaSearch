package ru.sharanov.aviasearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AviaSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(AviaSearchApplication.class, args);
        System.out.println("Сервис поиска авиабилетов\n\n");
//        ConfigurableApplicationContext cac = SpringApplication.run(AviaSearchApplication.class, args);
//        cac.close();
    }
}