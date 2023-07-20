package ru.sharanov.aviasearch.handler;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MainMenu {

    public static String mainMenu() {
        System.out.println("""
                Главное меню
                                
                1 - ввод рейса
                2 - вывод всех рейсов
                3 - поиск рейса по номеру
                0 - завершение работы
                                
                Введите номер пункта меню: """);

       return new Scanner(System.in).nextLine();
    }
}