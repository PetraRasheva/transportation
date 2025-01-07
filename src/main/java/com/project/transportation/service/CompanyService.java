package com.project.transportation.service;

import com.project.transportation.dto.CompanyDto;

import java.time.LocalDateTime;
import java.util.List;

public interface CompanyService {
    CompanyDto addCompany(CompanyDto company);

    CompanyDto updateCompany(CompanyDto company);

    CompanyDto getCompanyById(Integer id);

    void deleteCompany(Integer id);

    List<CompanyDto> getCompaniesSortedByIncome(boolean ascending);

    List<CompanyDto> getCompaniesByIncomeRange(double minIncome, double maxIncome);

    double getCompanyIncomeByDateRange(Integer id, LocalDateTime startDate, LocalDateTime endDate);
}
