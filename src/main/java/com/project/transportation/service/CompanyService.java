package com.project.transportation.service;

import com.project.transportation.dto.CompanyDto;

public interface CompanyService {
    CompanyDto addCompany(CompanyDto company);

    CompanyDto updateCompany(CompanyDto company);

    CompanyDto getCompanyById(Integer id);

    void deleteCompany(Integer id);
}
