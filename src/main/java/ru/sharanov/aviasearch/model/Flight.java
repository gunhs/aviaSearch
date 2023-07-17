package ru.sharanov.aviasearch.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Flight {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String number;
    private LocalDate departureTime;
    private LocalTime durationFlight;
    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;
    @ManyToOne
    @JoinColumn(name = "arrive_airport_id")
    private Airport arriveAirport;
    private double price;

    public Flight(String number, LocalDate departureTime, LocalTime durationFlight, Airport departureAirport, Airport arriveAirport, double price) {
        this.number = number;
        this.departureTime = departureTime;
        this.durationFlight = durationFlight;
        this.departureAirport = departureAirport;
        this.arriveAirport = arriveAirport;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Flight flight = (Flight) o;
        return id != null && Objects.equals(id, flight.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}