package ru.sharanov.aviasearch.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AirportDTO {
    private String cityCode;
    private String countryCode;
    private String timeZone;
    private Boolean flightable;
    private String name;
    private String code;
    private String iataType;
}
