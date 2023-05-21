package com.kaliada.tickets.repository;

import com.kaliada.tickets.entity.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AirportRepository extends JpaRepository<Airport, Long> {
    Optional<Airport> findAirportByCode(String code);
}
