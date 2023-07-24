package ru.sharanov.aviasearch.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sharanov.aviasearch.model.Airport;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

}
