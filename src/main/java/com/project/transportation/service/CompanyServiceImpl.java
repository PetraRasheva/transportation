package com.project.transportation.service;

import com.project.transportation.dto.CompanyDto;
import com.project.transportation.exception.CompanyNotFoundException;
import com.project.transportation.mapper.CompanyMapper;
import com.project.transportation.model.Company;
import com.project.transportation.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository, CompanyMapper companyMapper) {
        this.companyRepository = companyRepository;
        this.companyMapper = companyMapper;
    }
    @Override
    public CompanyDto addCompany(CompanyDto companyDto) {
        Company company = companyRepository.save(companyMapper.toEntity(companyDto));
        return companyMapper.toDto(company);
    }

    @Override
    public CompanyDto updateCompany(CompanyDto companyDto) {
        Company company = companyRepository.findCompanyById(companyDto.id())
                .orElseThrow(() -> new CompanyNotFoundException("Company with id " + companyDto.id() + " was not found"));
        companyMapper.updateCompanyFromDto(companyDto, company);
        return companyMapper.toDto(companyRepository.save(company));
    }

    @Override
    public CompanyDto getCompanyById(Integer id) {
        Company company = companyRepository.findCompanyById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company with id " + id + " was not found"));
        return companyMapper.toDto(company);
    }

    @Override
    public void deleteCompany(Integer id) {
        companyRepository.deleteCompanyById(id);
    }

    public List<CompanyDto> getCompaniesSortedByIncome(boolean ascending) {
        List<Company> companies;
        if (ascending) {
            companies = companyRepository.findAllByOrderByIncomeAsc(); // Ensure this method exists in the repository
        } else {
            companies = companyRepository.findAllByOrderByIncomeDesc(); // Ensure this method exists in the repository
        }
        return companies.stream()
                .map(companyMapper::toDto) // Convert each Company to CompanyDto
                .collect(Collectors.toList());
    }

    // Get companies filtered by income range
    public List<CompanyDto> getCompaniesByIncomeRange(double minIncome, double maxIncome) {
        List<Company> companies = companyRepository.findByIncomeRange(minIncome, maxIncome); // Ensure this method exists
        return companies.stream()
                .map(companyMapper::toDto) // Convert each Company to CompanyDto
                .collect(Collectors.toList());
    }

}
