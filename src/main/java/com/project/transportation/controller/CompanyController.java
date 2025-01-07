package com.project.transportation.controller;

import com.project.transportation.dto.CompanyDto;
import com.project.transportation.exception.CompanyNotFoundException;
import com.project.transportation.service.CompanyService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
        if (company == null) {
            throw new CompanyNotFoundException("Company not found with id: " + id);
        }
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
    @GetMapping("/sorted-by-income")
    public ResponseEntity<List<CompanyDto>> getCompaniesSortedByIncome(@RequestParam(defaultValue = "true") boolean ascending) {
        List<CompanyDto> companyDtos = companyService.getCompaniesSortedByIncome(ascending);
        return ResponseEntity.ok(companyDtos);
    }

    // Endpoint to get companies by income range
    @GetMapping("/filter-by-income")
    public ResponseEntity<List<CompanyDto>> getCompaniesByIncomeRange(@RequestParam double min, @RequestParam double max) {
        List<CompanyDto> companyDtos = companyService.getCompaniesByIncomeRange(min, max);
        return ResponseEntity.ok(companyDtos);
    }

    // Endpoint to get the income for the company in the specified date range
    @GetMapping("/{id}/income")
    public ResponseEntity<Double> getCompanyIncomeByDateRange(
            @PathVariable Integer id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime to) {
        double income = companyService.getCompanyIncomeByDateRange(id, from, to);
        return ResponseEntity.ok(income);
    }

}