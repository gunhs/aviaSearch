package ru.sharanov.aviasearch.handler;

import org.springframework.stereotype.Component;
import ru.sharanov.aviasearch.service.FlightsService;

import java.util.Scanner;

@Component
public class Menu {

    private final FlightsService flightService;

    Menu(FlightsService flightService) {
        this.flightService = flightService;
    }

    public  void mainMenu() {
        System.out.println("""
                Главное меню
                                
                1 - ввод рейса
                2 - вывод всех рейсов
                3 - поиск рейса по номеру
                0 - завершение работы
                                
                Введите номер пункта меню: """);

        String input = new Scanner(System.in).nextLine();

        switch (input) {
            case "1" -> findFlight();
            case "2" -> showFlight();
            case "3" -> findFlightByNumber();
            case "0" -> System.exit(1);
            default -> System.out.println("Введите пункт меню");
        }
    }

    private static void findFlightByNumber() {

    }

    private static void showFlight() {
    }

    private void findFlight() {
        System.out.println("Введите данные рейса:\n ХХХХ - номер рейса: ");
        String flightNumber = new Scanner(System.in).nextLine();
        System.out.println("ДД/ММ/ГГГГ - дата рейса: ");
        String departureDate = new Scanner(System.in).nextLine();
        System.out.println("ЧЧ/ММ - время вылета: ");
        String departureTime = new Scanner(System.in).nextLine();
        System.out.println("ХХ.ХХ - длительность полёта: ");
        String durationFlight = new Scanner(System.in).nextLine();
        System.out.println("ХХХ - аэропорт вылета: ");
        String departureAirport = new Scanner(System.in).nextLine();
        System.out.println("ХХХ - аэропорт назначения: ");
        String arriveAirport = new Scanner(System.in).nextLine();
        System.out.println(".ХХ - стоимость билета: ");
        String price = new Scanner(System.in).nextLine();

        flightService.addFlight(flightNumber, departureDate, departureTime,
                durationFlight, departureAirport, arriveAirport, price);
    }
}
