package ru.sharanov.aviasearch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.sharanov.aviasearch.config.FileJSON;
import ru.sharanov.aviasearch.dto.AirportDTO;
import ru.sharanov.aviasearch.model.Airport;
import ru.sharanov.aviasearch.repositoris.AirportRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class AirportService {

    private final AirportRepository airportRepository;

    AirportService(AirportRepository airportRepository, FileJSON fileJSON) throws IOException {
        this.airportRepository = airportRepository;
        if (!fileJSON.getPath().isEmpty()) {
            parseFileWithAirports(fileJSON.getPath());
        }
    }

    private void parseFileWithAirports(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);

        File file = new File(path);
        List<AirportDTO> airportDTOs = objectMapper.readValue(file, new TypeReference<>() {
        });
//        airportDTOs.forEach((k,v) -> System.out.println(k + " " + v));

        airportDTOs.forEach(a -> {
            Airport airport = new Airport();
            airport.setCodeIATA(a.getCode());
            airportRepository.save(airport);
        });
    }
}
