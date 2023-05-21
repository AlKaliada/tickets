package com.kaliada.tickets.repository;

import com.kaliada.tickets.entity.Airport;
import com.kaliada.tickets.entity.Company;
import com.kaliada.tickets.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    Optional<Flight> findFlightByCompanyAndToAirportAndFromAirportAndDateAndTime(Company company, Airport to, Airport from, LocalDate date, LocalTime time);
    Optional<Flight> findFlightByCompanyAndToAirportAndFromAirportAndDate(Company company, Airport to, Airport from, LocalDate date);
}
