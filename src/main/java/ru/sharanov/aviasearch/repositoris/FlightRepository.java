package ru.sharanov.aviasearch.repositoris;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sharanov.aviasearch.model.Flight;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
}
