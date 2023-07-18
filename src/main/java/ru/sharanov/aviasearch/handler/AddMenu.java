package ru.sharanov.aviasearch.handler;

import java.util.ArrayList;
import java.util.Scanner;

public class AddMenu {
    public static ArrayList<String> addFlight() {
        ArrayList<String> result = new ArrayList<>();
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
        result.add(flightNumber);
        result.add(departureDate);
        result.add(departureTime);
        result.add(durationFlight);
        result.add(departureAirport);
        result.add(arriveAirport);
        result.add(price);
        return result;
    }
}
