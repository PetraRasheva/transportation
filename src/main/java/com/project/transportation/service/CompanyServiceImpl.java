package com.project.transportation.service;

import com.project.transportation.dto.CompanyDto;
import com.project.transportation.exception.CompanyNotFoundException;
import com.project.transportation.mapper.CompanyMapper;
import com.project.transportation.model.Company;
import com.project.transportation.repository.CompanyRepository;
import org.springframework.stereotype.Service;

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
}
