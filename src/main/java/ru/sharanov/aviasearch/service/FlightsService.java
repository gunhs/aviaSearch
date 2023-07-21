package ru.sharanov.aviasearch.service;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import ru.sharanov.aviasearch.handler.AddMenu;
import ru.sharanov.aviasearch.handler.MainMenu;
import ru.sharanov.aviasearch.model.Airport;
import ru.sharanov.aviasearch.model.Flight;
import ru.sharanov.aviasearch.repositoris.AirportRepository;
import ru.sharanov.aviasearch.repositoris.FlightRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

@Service
public class FlightsService {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final AddMenu addMenu;
    private final MainMenu mainMenu;
    private final ApplicationContext applicationContext;


    public FlightsService(FlightRepository flightRepository, AirportRepository airportRepository, AddMenu addMenu,
                          MainMenu mainMenu, ApplicationContext applicationContext) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        this.addMenu = addMenu;
        this.mainMenu = mainMenu;
        this.applicationContext = applicationContext;
        callMainMenu();
    }

    private void addFlight() {
        ArrayList<String> components = addMenu.addFlight();
        String flightNumber = components.get(0);
        String fullTime = components.get(2) + " " + components.get(1);
        String durationFlight = components.get(3);
        String departureAirportCodeIATA = components.get(4);
        String arriveAirportCodeIATA = components.get(5);
        String price = components.get(6);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        DateTimeFormatter formatterForDurationFlight = DateTimeFormatter.ofPattern("HH.mm");
        LocalDateTime departureTimeDate = LocalDateTime.parse(fullTime, formatter);
        LocalTime durationFlightTime = LocalTime.parse(durationFlight, formatterForDurationFlight);
        double priceDigit = Double.parseDouble(price);
        Airport departureAirport = airportRepository.findAll().stream()
                .filter(a -> a.getCodeIATA().equals(departureAirportCodeIATA)).findFirst().orElse(null);
        Airport arriveAirport = airportRepository.findAll().stream()
                .filter(a -> a.getCodeIATA().equals(arriveAirportCodeIATA)).findFirst().orElse(null);

        Flight flight = new Flight(flightNumber, departureTimeDate,
                durationFlightTime, departureAirport,
                arriveAirport, priceDigit);
        flightRepository.save(flight);

        callMainMenu();
    }

    private void showFlights() {
        flightRepository.findAll().forEach(System.out::println);
        callMainMenu();
    }

    private void findFlightByNumber() {
        System.out.println("Введите номер рейса в формате XXXX: ");
        String number = new Scanner(System.in).nextLine();
        Flight flight = flightRepository.findFlightByNumber(number);
        System.out.println(flight);
        callMainMenu();
    }

    private void callMainMenu() {
        String menuPoint = mainMenu.mainMenu();
        switch (menuPoint) {
            case "1" -> addFlight();
            case "2" -> showFlights();
            case "3" -> findFlightByNumber();
            case "0" -> SpringApplication.exit(applicationContext);
            default -> System.out.println("Введите пункт меню");
        }
    }
}
