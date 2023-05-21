package com.kaliada.tickets.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(
        name = "flights",
        uniqueConstraints = {@UniqueConstraint(name = "constr", columnNames = {"company_name", "from_airport", "to_airport", "date", "time"})}
)
public class Flight {

    public Flight() {

    }

    public Flight(Company company, Airport fromAirport, Airport toAirport, LocalDate date, LocalTime time, LocalTime duration) {
        this.company = company;
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
        this.date = date;
        this.time = time;
        this.duration = duration;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_name")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_airport")
    private Airport fromAirport;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_airport")
    private Airport toAirport;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "duration")
    private LocalTime duration;
}
