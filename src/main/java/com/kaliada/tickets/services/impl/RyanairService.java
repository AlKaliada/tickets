package com.kaliada.tickets.services.impl;

import com.kaliada.tickets.entity.*;
import com.kaliada.tickets.model.Destination;
import com.kaliada.tickets.model.Prices;
import com.kaliada.tickets.repository.FlightRepository;
import com.kaliada.tickets.repository.PriceRepository;
import com.kaliada.tickets.repository.TodayActivitiesRepository;
import com.kaliada.tickets.services.ApiService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RyanairService {

    private static final Logger logger = LoggerFactory.getLogger(RyanairService.class);

    private final AirportService airportService;

    private final CompanyService companyService;

    private final ApiService apiService;

    private final PriceRepository priceRepository;

    private final FlightRepository flightRepository;

    private final TodayActivitiesRepository todayActivitiesRepository;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Value("${ryanair.airports}")
    private String airportsUrl;

    @Value("${ryanair.routes}")
    private String routesUrl;

    @Value("${ryanair.availabilities}")
    private String availabilitiesUrl;

    @Value("${ryanair.flight}")
    private String flightUrl;

    public void updateData() {
        airportService.updateAirports("Ryanair", airportsUrl);
        Company company = companyService.getCompanyByName("Ryanair");
        if (company == null) {
            return;
        }
        List<Airport> airports = company.getAirports();
        List<String> updated = todayActivitiesRepository.findAll()
                .stream()
                .filter(a -> a.getDate().equals(LocalDate.now()))
                .map(a -> String.join(",", a.getCompany(), a.getFromAirport(), a.getToAirport()))
                .toList();
        for (Airport airport : airports) {
            List<Airport> destinations = getDestinations(airport.getCode())
                    .stream()
                    .map(air -> airportService.getAirport(airports, air.getCode()))
                    .toList();
            for (Airport destination : destinations) {
                if (updated.contains(String.join(",", company.getName(), airport.getCode(), destination.getCode()))) {
                    continue;
                }
                List<Price> prices = getPrices(company, destination, airport);
                try {
                    priceRepository.saveAll(prices);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                }
            }
        }
        todayActivitiesRepository.deleteAll();
    }

    private List<Destination> getDestinations(String code) {
        return new ArrayList<>(Arrays.asList(apiService.getArrayResponseContent(String.format(routesUrl, code), Destination[].class)));
    }

    private List<LocalDate> getFlightDates(String origin, String destination) {
        return Arrays.stream(apiService.getArrayResponseContent(String.format(availabilitiesUrl, origin, destination), String[].class))
                .map(date -> LocalDate.parse(date, formatter)).collect(Collectors.toList());
    }

    private List<Price> getPrices(Company company, Airport destinationAirport, Airport airport) {
        if (!isValidAirport(destinationAirport) && !isValidAirport(airport)) {
            return Collections.emptyList();
        }
        List<Price> prices = new ArrayList<>();
        List<LocalDate> flightDates = getFlightDates(airport.getCode(), destinationAirport.getCode());
        for (LocalDate flightDate : flightDates) {
            LocalDate today = LocalDate.now();
            try {
                Prices pricesWrapper = apiService.getResponseContent(String.format(flightUrl, flightDate, destinationAirport.getCode(), airport.getCode()), Prices.class);
                for (Price price : pricesWrapper.getPrices()) {
                    Flight flight = flightRepository.findFlightByCompanyAndToAirportAndFromAirportAndDateAndTime(company, destinationAirport, airport, flightDate, price.getTime())
                            .orElse(null);
                    if (flight == null) {
                        flight = new Flight(company, airport, destinationAirport, flightDate, price.getTime(), price.getDuration());
                        flightRepository.save(flight);
                    }
                    price.setSearchTime(today);
                    price.setFlight(flight);
                    prices.add(price);
                }
            } catch (Exception e) {
                logger.error(e.getMessage());
                System.out.println(e.getMessage());
            }
        }
        todayActivitiesRepository.save(new TodayActivities(company.getName(), airport.getCode(), destinationAirport.getCode(), LocalDate.now()));
        return prices;
    }

    private boolean isValidAirport(Airport airport) {
        return airport.getCity().equalsIgnoreCase("Warsaw Modlin")
                || airport.getCity().equalsIgnoreCase("Warsaw Chopin")
                || airport.getCity().equalsIgnoreCase("Vilnius");
    }
}
