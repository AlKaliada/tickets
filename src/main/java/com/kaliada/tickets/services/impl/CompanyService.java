package com.kaliada.tickets.services.impl;

import com.kaliada.tickets.entity.Company;
import com.kaliada.tickets.repository.CompanyRepository;
import com.kaliada.tickets.services.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyService extends BaseService<Company, CompanyRepository> {

    private final CompanyRepository companyRepository;

    public Company getCompanyByName(String name) {
        return companyRepository.findCompanyByName(name).orElse(null);
    }
}
