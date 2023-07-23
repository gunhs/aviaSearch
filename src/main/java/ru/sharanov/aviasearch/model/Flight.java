package ru.sharanov.aviasearch.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
public class Flight {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private LocalDateTime departureTime;
    private LocalTime durationFlight;
    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;
    @ManyToOne
    @JoinColumn(name = "arrive_airport_id")
    private Airport arriveAirport;
    private double price;

    public Flight(String number, LocalDateTime departureTime, LocalTime durationFlight, Airport departureAirport, Airport arriveAirport, double price) {
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

    @Override
    public String toString() {
        return "Информация о рейсе: " + number + " " +
                departureTime.getDayOfMonth() + "/" + departureTime.getMonth().getValue() + "/"
                + departureTime.getYear() + " " +
                departureTime.getHour() + ":" + departureTime.getMinute() + " " +
                durationFlight.getHour() + "." + durationFlight.getMinute() + " " +
                departureAirport.getCodeIATA() + " " +
                arriveAirport.getCodeIATA() + " " +
                price;
    }
}