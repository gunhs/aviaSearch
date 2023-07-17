package ru.sharanov.aviasearch.service;

import org.springframework.stereotype.Service;
import ru.sharanov.aviasearch.model.Airport;
import ru.sharanov.aviasearch.model.Flight;
import ru.sharanov.aviasearch.repositoris.AirportRepository;
import ru.sharanov.aviasearch.repositoris.FlightRepository;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class FlightsService {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;

    public FlightsService(FlightRepository flightRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }

    public void addFlight(String flightNumber, String departureDate,
                          String departureTime, String durationFlight,
                          String departureAirportCodeIATA, String arriveAirportCodeIATA
            , String price) {

        String fullTime = departureTime + " " + departureDate;
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
}
