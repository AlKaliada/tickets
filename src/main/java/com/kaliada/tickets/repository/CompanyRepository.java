package com.kaliada.tickets.repository;

import com.kaliada.tickets.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findCompanyByName(String name);
}
