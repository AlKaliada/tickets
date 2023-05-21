package com.kaliada.tickets.services.impl;

import com.kaliada.tickets.entity.Flight;
import com.kaliada.tickets.repository.FlightRepository;
import com.kaliada.tickets.services.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FlightService extends BaseService<Flight, FlightRepository> {


}
