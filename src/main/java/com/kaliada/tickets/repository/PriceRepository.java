package com.kaliada.tickets.repository;

import com.kaliada.tickets.entity.Flight;
import com.kaliada.tickets.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface PriceRepository extends JpaRepository<Price, Long> {
    Optional<Price> findByFlightAndSearchTime(Flight flight, LocalDate searchTime);
}
