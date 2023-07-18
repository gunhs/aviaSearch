package ru.sharanov.aviasearch.service;

import org.springframework.stereotype.Service;
import ru.sharanov.aviasearch.handler.AddMenu;
import ru.sharanov.aviasearch.handler.MainMenu;
import ru.sharanov.aviasearch.model.Airport;
import ru.sharanov.aviasearch.model.Flight;
import ru.sharanov.aviasearch.repositoris.AirportRepository;
import ru.sharanov.aviasearch.repositoris.FlightRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@Service
public class FlightsService {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    public FlightsService(FlightRepository flightRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
        String menuPoint = MainMenu.mainMenu();
        switch (menuPoint) {
            case "1" -> addFlight();
            case "2" -> showFlights();
            case "3" -> findFlightByNumber();
            case "0" -> System.exit(1);
            default -> System.out.println("Введите пункт меню");
        }
    }

    private void addFlight() {

        ArrayList<String> components = AddMenu.addFlight();
        String flightNumber = components.get(0);
        String fullTime = components.get(1) + " " + components.get(2);
        String durationFlight = components.get(3);
        String departureAirportCodeIATA = components.get(4);
        String arriveAirportCodeIATA = components.get(5);
        String price = components.get(6);

        LocalDate departureTimeDate = LocalDate.parse(fullTime);
        LocalTime durationFlightTime = LocalTime.parse(durationFlight);
        double priceDigit = Double.parseDouble(price);
        Airport departureAirport = airportRepository.findAll().stream()
                .filter(a -> a.getCodeIATA().equals(departureAirportCodeIATA)).findFirst().orElse(null);
        Airport arriveAirport = airportRepository.findAll().stream()
                .filter(a -> a.getCodeIATA().equals(arriveAirportCodeIATA)).findFirst().orElse(null);

        Flight flight = new Flight(flightNumber, departureTimeDate,
                durationFlightTime, departureAirport,
                arriveAirport, priceDigit);
        flightRepository.save(flight);

    }

    private void showFlights() {

    }

    private void findFlightByNumber() {
    }
}
