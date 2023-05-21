package com.kaliada.tickets.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "todayactivities")
public class TodayActivities {

    public TodayActivities() {
    }

    public TodayActivities(String company, String fromAirport, String toAirport, LocalDate date) {
        this.company = company;
        this.fromAirport = fromAirport;
        this.toAirport = toAirport;
        this.date = date;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Company")
    private String company;

    @Column(name = "from_airport")
    private String fromAirport;

    @Column(name = "to_airport")
    private String toAirport;

    @Column(name = "date")
    private LocalDate date;
}
