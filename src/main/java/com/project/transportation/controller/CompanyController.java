package com.project.transportation.controller;


import com.project.transportation.dto.CompanyDto;
import com.project.transportation.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private final CompanyService companyService;

    CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<CompanyDto> getCompanyById(@PathVariable("id") Integer id) {
        CompanyDto company = companyService.getCompanyById(id);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<CompanyDto> addCompany(@RequestBody CompanyDto company) {
        CompanyDto newCompany = companyService.addCompany(company);
        return new ResponseEntity<>(newCompany, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<CompanyDto> updateCompany(@RequestBody CompanyDto company) {
        CompanyDto updateCompany = companyService.updateCompany(company);
        return new ResponseEntity<>(updateCompany, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable("id") Integer id) {
        companyService.deleteCompany(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // Endpoint to get companies sorted by income
    @GetMapping("/sorted")
    public List<CompanyDto> getCompaniesSortedByIncome(@RequestParam(defaultValue = "true") boolean ascending) {
        return companyService.getCompaniesSortedByIncome(ascending);
    }

    // Endpoint to get companies by income range
    @GetMapping("/filter")
    public List<CompanyDto> getCompaniesByIncomeRange(@RequestParam double minIncome, @RequestParam double maxIncome) {
        return companyService.getCompaniesByIncomeRange(minIncome, maxIncome);
    }

}