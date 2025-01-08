package com.project.transportation.controller;

import com.project.transportation.dto.CompanyDto;
import com.project.transportation.exception.CompanyNotFoundException;
import com.project.transportation.service.CompanyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompanyControllerTest {

    @Mock
    private CompanyService companyService;  // Mock the service

    @InjectMocks
    private CompanyController companyController;  // Inject the mock service into the controller

    private CompanyDto companyDto;

    @BeforeEach
    void setUp() {
        // Initialize a test CompanyDto
        companyDto = new CompanyDto(1, "Test Company", 1000.0);
    }

    @Test
    void testGetCompanyById() {
        // Arrange: Define behavior of the mock service
        when(companyService.getCompanyById(1)).thenReturn(companyDto);

        // Act: Call the controller method
        ResponseEntity<CompanyDto> response = companyController.getCompanyById(1);

        // Assert: Verify the response
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(companyDto);

        // Verify that the service method was called once
        verify(companyService, times(1)).getCompanyById(1);
    }

    @Test
    void testGetCompanyById_NotFound() {
        // Arrange: Simulate the company not being found by throwing a CompanyNotFoundException
        when(companyService.getCompanyById(1)).thenReturn(null);

        // Act & Assert: Verify that the controller throws a ResourceNotFoundException
        assertThatThrownBy(() -> companyController.getCompanyById(1))
                .isInstanceOf(CompanyNotFoundException.class)
                .hasMessage("Company not found with id: 1");
    }

    @Test
    void testAddCompany() {
        // Arrange: Define behavior of the mock service
        when(companyService.addCompany(any(CompanyDto.class))).thenReturn(companyDto);

        // Act: Call the controller method
        ResponseEntity<CompanyDto> response = companyController.addCompany(companyDto);

        // Assert: Verify the response
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(companyDto);

        // Verify that the service method was called once
        verify(companyService, times(1)).addCompany(companyDto);
    }

    @Test
    void testUpdateCompany() {
        // Arrange: Define behavior of the mock service
        when(companyService.updateCompany(any(CompanyDto.class))).thenReturn(companyDto);

        // Act: Call the controller method
        ResponseEntity<CompanyDto> response = companyController.updateCompany(companyDto);

        // Assert: Verify the response
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(companyDto);

        // Verify that the service method was called once
        verify(companyService, times(1)).updateCompany(companyDto);
    }

    @Test
    void testDeleteCompany() {
        // Act: Call the controller method
        ResponseEntity<?> response = companyController.deleteCompany(1);

        // Assert: Verify the response
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Verify that the service method was called once
        verify(companyService, times(1)).deleteCompany(1);
    }

    @Test
    void testGetCompaniesSortedByIncome() {
        // Arrange: Mock the list of companies returned by the service
        List<CompanyDto> sortedCompanies = List.of(
                new CompanyDto(1, "Company A", 5000.0),
                new CompanyDto(2, "Company B", 10000.0),
                new CompanyDto(3, "Company C", 15000.0)
        );

        when(companyService.getCompaniesSortedByIncome(true)).thenReturn(sortedCompanies);

        // Act: Call the controller method
        ResponseEntity<List<CompanyDto>> response = companyController.getCompaniesSortedByIncome(true);

        // Assert: Verify the response
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(sortedCompanies);

        // Verify that the service method was called once
        verify(companyService, times(1)).getCompaniesSortedByIncome(true);
    }

    @Test
    void testGetCompaniesByIncomeRange() {
        // Arrange: Mock the list of companies returned by the service
        List<CompanyDto> filteredCompanies = List.of(
                new CompanyDto(1, "Company A", 5000.0),
                new CompanyDto(2, "Company B", 8000.0)
        );

        when(companyService.getCompaniesByIncomeRange(4000.0, 9000.0)).thenReturn(filteredCompanies);

        // Act: Call the controller method
        ResponseEntity<List<CompanyDto>> response = companyController.getCompaniesByIncomeRange(4000.0, 9000.0);

        // Assert: Verify the response
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(filteredCompanies);

        // Verify that the service method was called once
        verify(companyService, times(1)).getCompaniesByIncomeRange(4000.0, 9000.0);
    }

    @Test
    void testGetCompaniesIncomeByDateRange() {
        // Arrange: Define the income value for the specified company and date range
        double expectedIncome = 10000.0;

        when(companyService.getCompanyIncomeByDateRange(1,
                LocalDateTime.parse("2024-01-01T00:00:00"),
                LocalDateTime.parse("2024-01-31T23:59:59"))).thenReturn(expectedIncome);

        // Act: Call the controller method
        ResponseEntity<Double> response = companyController.getCompanyIncomeByDateRange(
                1,
                LocalDateTime.parse("2024-01-01T00:00:00"),
                LocalDateTime.parse("2024-01-31T23:59:59")
        );

        // Assert: Verify the response
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(expectedIncome);

        // Verify that the service method was called once
        verify(companyService, times(1)).getCompanyIncomeByDateRange(1,
                LocalDateTime.parse("2024-01-01T00:00:00"),
                LocalDateTime.parse("2024-01-31T23:59:59"));
    }
}
