package com.kaliada.tickets.services;

import com.kaliada.tickets.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AirCompanyService {
    @Autowired
    private AirportRepository airportRepository;


}
