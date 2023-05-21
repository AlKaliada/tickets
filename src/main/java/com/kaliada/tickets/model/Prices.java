package com.kaliada.tickets.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kaliada.tickets.entity.Price;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Prices {

    List<Price> prices;

    @JsonProperty("trips")
    private void unpackCode(List<Object> objects) {
        prices = new ArrayList<>();
        objects.forEach(o -> {
            Map<String, Object> map = (Map<String, Object>) o;
            List<Object> dates = (List<Object>) map.get("dates");
            Map<String, Object> date = (Map<String, Object>) dates.get(0);
            List<Object> flights = (List<Object>) date.get("flights");
            Map<String, Object> flight = (Map<String, Object>) flights.get(0);
            Price price = new Price();
            List<Object> times = (List<Object>) flight.get("time");
            Map<String, Object> regularFare = (Map<String, Object>) flight.get("regularFare");
            List<Object> fares = (List<Object>) regularFare.get("fares");
            Map<String, Object> fare = (Map<String, Object>) fares.get(0);
            price.setDuration(LocalTime.parse((String) flight.get("duration")));
            String time = (String) times.get(0);
            price.setTime(LocalTime.parse(StringUtils.substringAfter(time, "T")));
            price.setPrice((Double) fare.get("amount"));
            prices.add(price);
        });
    }
}
