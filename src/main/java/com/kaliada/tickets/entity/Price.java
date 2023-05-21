package com.kaliada.tickets.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(
        name = "prices",
        uniqueConstraints = {@UniqueConstraint(name = "constr", columnNames = {"searchTime", "flight"})}
)
public class Price {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "searchTime")
    private LocalDate searchTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight")
    private Flight flight;

    @Column(name = "price")
    private Double price;

    @Transient
    private LocalTime duration;

    @Transient
    private LocalTime time;
}
