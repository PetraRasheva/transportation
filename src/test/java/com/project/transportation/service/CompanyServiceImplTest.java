package com.project.transportation.service;

import com.project.transportation.dto.CompanyDto;
import com.project.transportation.mapper.CompanyMapper;
import com.project.transportation.model.Company;
import com.project.transportation.model.Transportation;
import com.project.transportation.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceImplTest {

    @Mock
    private CompanyRepository companyRepository;

    @Mock
    private CompanyMapper companyMapper;

    @InjectMocks
    private CompanyServiceImpl companyService;

    private CompanyDto companyDto;
    private Company company;

    @BeforeEach
    void setUp() {
        company = new Company();
        company.setName("Test Company");
        company.setIncome(10000.0);

        // Mock relationships
        company.setEmployees(new HashSet<>());
        company.setTransportations(new HashSet<>());
        company.setVehicles(new HashSet<>());

        companyDto = new CompanyDto(1, "Test Company", 10000.0);
    }

    @Test
    void testAddCompany() {
        when(companyMapper.toEntity(companyDto)).thenReturn(company);
        when(companyRepository.save(company)).thenReturn(company);
        when(companyMapper.toDto(company)).thenReturn(companyDto);

        CompanyDto result = companyService.addCompany(companyDto);

        assertThat(result).isEqualTo(companyDto);
        verify(companyRepository, times(1)).save(company);
    }

    @Test
    void testGetCompanyById() {
        when(companyRepository.findCompanyById(1)).thenReturn(Optional.of(company));
        when(companyMapper.toDto(company)).thenReturn(companyDto);

        CompanyDto result = companyService.getCompanyById(1);

        assertThat(result).isEqualTo(companyDto);
        verify(companyRepository, times(1)).findCompanyById(1);
    }

    @Test
    void testUpdateCompany() {
        when(companyRepository.findCompanyById(1)).thenReturn(Optional.of(company));
        when(companyRepository.save(company)).thenReturn(company);
        when(companyMapper.toDto(company)).thenReturn(companyDto);

        CompanyDto result = companyService.updateCompany(companyDto);

        assertThat(result).isEqualTo(companyDto);
        verify(companyRepository, times(1)).findCompanyById(1);
        verify(companyRepository, times(1)).save(company);
    }

    @Test
    void testDeleteCompany() {
        doNothing().when(companyRepository).deleteCompanyById(1);

        companyService.deleteCompany(1);

        verify(companyRepository, times(1)).deleteCompanyById(1);
    }

    @Test
    void testGetCompaniesSortedByIncome() {
        List<Company> companies = List.of(
                new Company( "Company A", 5000.0),
                new Company( "Company B", 10000.0),
                new Company( "Company C", 15000.0)
        );
        List<CompanyDto> sortedCompanies = companies.stream()
                .map(c -> new CompanyDto(c.getId(), c.getName(), c.getIncome()))
                .toList();

        when(companyRepository.findAllByOrderByIncomeAsc()).thenReturn(companies);
        when(companyMapper.toDto(any(Company.class))).thenAnswer(invocation -> {
            Company c = invocation.getArgument(0);
            return new CompanyDto(c.getId(), c.getName(), c.getIncome());
        });

        List<CompanyDto> result = companyService.getCompaniesSortedByIncome(true);

        assertThat(result).isEqualTo(sortedCompanies);
        verify(companyRepository, times(1)).findAllByOrderByIncomeAsc();
    }

    @Test
    void testGetCompanyIncomeByDateRange() {
        Transportation t1 = new Transportation();
        t1.setStartDate(LocalDateTime.of(2023, 1, 1, 0, 0));
        t1.setPrice(2000.0);
        t1.setPaid(true);

        Transportation t2 = new Transportation();
        t2.setStartDate(LocalDateTime.of(2023, 2, 1, 0, 0));
        t2.setPrice(3000.0);
        t2.setPaid(true);

        company.setTransportations(Set.of(t1, t2));

        when(companyRepository.findById(1)).thenReturn(Optional.of(company));

        double result = companyService.getCompanyIncomeByDateRange(
                1,
                LocalDateTime.of(2023, 1, 1, 0, 0),
                LocalDateTime.of(2023, 12, 31, 0, 0)
        );

        assertThat(result).isEqualTo(5000.0);
        verify(companyRepository, times(1)).findById(1);
    }

}
