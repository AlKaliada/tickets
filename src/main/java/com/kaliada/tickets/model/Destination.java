package com.kaliada.tickets.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Destination {

    private String code;

    @JsonProperty("arrivalAirport")
    private void unpackCode(Map<String, Object> object) {
        object.forEach((key, value) -> {
            if ("code".equals(key)) {
                code = (String) value;
            }
        });
    }
}
