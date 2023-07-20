package ru.sharanov.aviasearch.handler;

import org.springframework.stereotype.Component;
import ru.sharanov.aviasearch.repositoris.AirportRepository;

import java.util.ArrayList;
import java.util.Scanner;

@Component
public class AddMenu {

    private final AirportRepository airportRepository;

    public AddMenu(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    public ArrayList<String> addFlight() {
        ArrayList<String> result = new ArrayList<>();
        System.out.println("Введите данные рейса:\n ХХХХ - номер рейса: ");
        String flightNumber = "";
        do {
            flightNumber = new Scanner(System.in).nextLine();
        } while (!checkNumber(flightNumber));
        System.out.println("ДД/ММ/ГГГГ - дата рейса: ");
        String departureDate;
        do {
            departureDate = new Scanner(System.in).nextLine();
        } while (!checkDepartureDate(departureDate));
        System.out.println("ЧЧ:ММ - время вылета: ");
        String departureTime;
        do {
            departureTime = new Scanner(System.in).nextLine();
        } while (!checkDepartureTime(departureTime));
        System.out.println("ХХ.ХХ - длительность полёта: ");
        String durationFlight;
        do {
            durationFlight = new Scanner(System.in).nextLine();
        } while (!checkDurationFlight(durationFlight));
        System.out.println("ХХХ - аэропорт вылета: ");
        String departureAirport;
        do {
            departureAirport = new Scanner(System.in).nextLine();
        } while (!checkDepartureAirport(departureAirport));
        System.out.println("ХХХ - аэропорт назначения: ");
        String arriveAirport;
        do {
            arriveAirport = new Scanner(System.in).nextLine();
        } while (!checkArriveAirport(arriveAirport));
        System.out.println(".ХХ - стоимость билета: ");
        String price;
        do {
            price = new Scanner(System.in).nextLine();
        } while (!checkPrice(price));
        result.add(flightNumber.toUpperCase());
        result.add(departureDate);
        result.add(departureTime);
        result.add(durationFlight);
        result.add(departureAirport.toUpperCase());
        result.add(arriveAirport.toUpperCase());
        result.add(price);
        return result;
    }

    private boolean checkNumber(String flightNumber) {
        if (!flightNumber.matches("[a-zA-Z0-9]{4}")) {
            System.out.println("Введён некорректный номер рейса\nВведите номер в формате ХХХХ, напрмиер 5B7N");
            return false;
        }
        return true;
    }

    private boolean checkDepartureDate(String departureDate) {
        if (!departureDate.matches("\\d{2}/\\d{2}/\\d{4}")) {
            System.out.println("Введена некорректная дата вылета рейса" +
                    "\nВведите номер в формате ДД/ММ/ГГГГ, например 01/01/1970");
            return false;
        }
        return true;
    }

    private boolean checkDepartureTime(String departureTime) {
        if (!departureTime.matches("\\d{2}:\\d{2}")) {
            System.out.println("Введено некорректное время рейса\nВведите время в формате ЧЧ:ММ, например 06:00");
            return false;
        }
        return true;
    }

    private boolean checkDurationFlight(String durationFlight) {
        if (!durationFlight.matches("\\d{2}\\.\\d{2}")) {
            System.out.println("Введено некорректное время полёта\nВведите время в формате ЧЧ.ММ, например 07.30");
            return false;
        }
        return true;
    }

    private boolean checkDepartureAirport(String departureAirport) {
        airportRepository.findAll();
        if (!departureAirport.matches("[a-zA-Z]{3}")) {
            System.out.println("Введён некорректный код аэропорта\nВведите код в формате ХХХ, например KUF");
            return false;
        }
        return true;
    }

    private boolean checkArriveAirport(String flightNumber) {
        if (!flightNumber.matches("[a-zA-Z]{3}")) {
            System.out.println("Введён некорректный код аэропорта\nВведите код в формате XXX, например LED");
            return false;
        }
        return true;
    }

    private boolean checkPrice(String price) {
        if (!price.matches("[0-9.]+")) {
            System.out.println("Введена некорректная стоимость билета\nВведите положительно число. Например 5000.00");
            return false;
        }
        return true;
    }

}
