package ru.sharanov.aviasearch.service;

import org.springframework.stereotype.Service;
import ru.sharanov.aviasearch.handler.AddMenu;
import ru.sharanov.aviasearch.handler.MainMenu;
import ru.sharanov.aviasearch.model.Airport;
import ru.sharanov.aviasearch.model.Flight;
import ru.sharanov.aviasearch.repositories.AirportRepository;
import ru.sharanov.aviasearch.repositories.FlightRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class FlightsService {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final AddMenu addMenu;
    private final MainMenu mainMenu;

    public FlightsService(FlightRepository flightRepository, AirportRepository airportRepository, AddMenu addMenu,
                          MainMenu mainMenu) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.addMenu = addMenu;
        this.mainMenu = mainMenu;
        System.out.println("Сервис поиска авиабилетов\n");
    }

    private void addFlight() {
        List<String> components = addMenu.addFlight();
        if (components.isEmpty()) {
            callMainMenu();
            return;
        }
        String flightNumber = components.get(0);
        String fullTime = components.get(2) + " " + components.get(1);
        String durationFlight = components.get(3);
        String departureAirportCodeIata = components.get(4);
        String arriveAirportCodeIata = components.get(5);
        String price = components.get(6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        DateTimeFormatter formatterForDurationFlight = DateTimeFormatter.ofPattern("HH.mm");
        LocalDateTime departureTimeDate = LocalDateTime.parse(fullTime, formatter);
        LocalTime durationFlightTime = LocalTime.parse(durationFlight, formatterForDurationFlight);
        double priceDigit = Double.parseDouble(price);
        Airport departureAirport = airportRepository.findAll().stream()
                .filter(a -> a.getCodeIata().equals(departureAirportCodeIata)).findFirst().orElse(null);
        Airport arriveAirport = airportRepository.findAll().stream()
                .filter(a -> a.getCodeIata().equals(arriveAirportCodeIata)).findFirst().orElse(null);

        Flight flight = new Flight(flightNumber, departureTimeDate, durationFlightTime, departureAirport,
                arriveAirport, priceDigit);
        flightRepository.save(flight);
        System.out.println("Информаци о рейсе " + flightNumber + " " + components.get(1) + " " + components.get(2) +
                " " + durationFlight + " " + departureAirportCodeIata + " " + arriveAirportCodeIata + " " + price +
                " добавлена\n");
        callMainMenu();
    }

    private void showFlights() {
        flightRepository.findAll().forEach(System.out::println);
        callMainMenu();
    }

    private void findFlightByNumber() {
        System.out.println("Введите номер рейса в формате XXXX: ");
        String number = new Scanner(System.in).nextLine();
        if (number.equals("0")) {
            callMainMenu();
        }
        Flight flight = flightRepository.findFlightByNumber(number);
        if (flight != null) {
            System.out.println(flight);
        } else {
            System.out.println("Информаия о рейсе не найдена");
        }
        callMainMenu();
    }

    public void callMainMenu() {
        String menuPoint = mainMenu.mainMenu();
        switch (menuPoint) {
            case "1" -> addFlight();
            case "2" -> showFlights();
            case "3" -> findFlightByNumber();
            case "0" -> System.out.println("Завершение работы программы");
            default -> {
                System.out.println("Введите пункт меню");
                callMainMenu();
            }
        }
    }
}
