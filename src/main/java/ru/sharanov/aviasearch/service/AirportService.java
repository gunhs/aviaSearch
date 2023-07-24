package ru.sharanov.aviasearch.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ru.sharanov.aviasearch.config.FileJsonList;
import ru.sharanov.aviasearch.dto.AirportDTO;
import ru.sharanov.aviasearch.model.Airport;
import ru.sharanov.aviasearch.repositories.AirportRepository;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class AirportService {
    private final AirportRepository airportRepository;

    AirportService(AirportRepository airportRepository, FileJsonList fileJSONList) {
        this.airportRepository = airportRepository;
        if (!fileJSONList.getFileJsonList().isEmpty()) {
            fileJSONList.getFileJsonList().forEach(f -> {
                try {
                    parseFileWithAirports(f);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void parseFileWithAirports(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_INVALID_SUBTYPE, false);

        File file = new File(path);
        List<AirportDTO> airportDTOs = objectMapper.readValue(file, new TypeReference<>() {
        });

        airportDTOs.forEach(a -> {
            Airport airport = new Airport();
            airport.setCodeIata(a.getCode());
            airportRepository.save(airport);
        });
    }
}
