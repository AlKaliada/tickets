package com.kaliada.tickets.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Entity
@Table(
        name = "airports",
        uniqueConstraints = {@UniqueConstraint(name = "constr", columnNames = {"code"})})
@JsonIgnoreProperties(ignoreUnknown = true)
public class Airport {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "code", unique = true)
    @JsonProperty("code")
    private String code;

    @Column(name = "city")
    @JsonProperty("name")
    private String city;

    @Column(name = "country")
    private String country;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "companiestoairports",
            joinColumns = @JoinColumn(name = "airportId"),
            inverseJoinColumns = @JoinColumn(name = "companyId")
    )
    @JsonIgnore
    private List<Company> companies = new ArrayList<>();

    @JsonProperty("country")
    private void unpackCountry(Map<String, Object> object) {
        object.forEach((key, value) -> {
            if ("name".equals(key)) {
                country = (String) value;
            }
        });
    }
}
