package ru.sharanov.aviasearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.sharanov.aviasearch.handler.Menu;

@SpringBootApplication
public class AviaSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(AviaSearchApplication.class, args);
        System.out.println("Сервис поиска авиабилетов\n\n");
        Menu menu = new Menu();
        start();
    }

    private void start(){
        menu.mainMenu();
    }
}