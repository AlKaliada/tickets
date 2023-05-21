package com.kaliada.tickets.services.impl;

import com.kaliada.tickets.entity.Airport;
import com.kaliada.tickets.entity.Company;
import com.kaliada.tickets.repository.AirportRepository;
import com.kaliada.tickets.repository.CompanyRepository;
import com.kaliada.tickets.services.ApiService;
import com.kaliada.tickets.services.BaseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirportService extends BaseService<Airport, AirportRepository> {

    private final AirportRepository airportRepository;
    private final CompanyRepository companyRepository;
    private final ApiService apiService;

    public void updateAirports(String companyName, String url) {
        Company company = companyRepository.findCompanyByName(companyName).orElseThrow(EntityNotFoundException::new);
        List<Airport> airports = getAirports(url);
        List<Airport> collect = airports.stream().map(airport -> airportRepository.findAirportByCode(airport.getCode()).orElse(airport)).collect(Collectors.toList());
        company.setAirports(collect);
        companyRepository.save(company);
    }

    public Airport getAirport(List<Airport> airports, String code) {
        return airports.stream()
                .filter(airport -> airport.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

    private List<Airport> getAirports(String url) {
        return new ArrayList<>(Arrays.asList(apiService.getArrayResponseContent(url, Airport[].class)));
    }


}
