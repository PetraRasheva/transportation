package com.project.transportation.repository;

import com.project.transportation.config.TestConfig;
import com.project.transportation.model.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Import(TestConfig.class)
public class CompanyRepositoryTest {

    @Autowired
    private CompanyRepository companyRepository;

    private Company company1;
    private Company company2;

    @BeforeEach
    public void setUp() {
        company1 = new Company();
        company1.setName("Company A");
        company1.setIncome(15000);

        company2 = new Company();
        company2.setName("Company B");
        company2.setIncome(30000);

        companyRepository.save(company1);
        companyRepository.save(company2);
    }

    @Test
    public void testFindCompanyById() {
        // Act
        Optional<Company> result = companyRepository.findById(company1.getId());

        // Assert
        assertThat(result).isPresent();
        assertThat(result.get().getName()).isEqualTo("Company A");
    }

    @Test
    public void testDeleteCompanyById() {
        // Act
        companyRepository.deleteCompanyById(2);
        Optional<Company> result = companyRepository.findById(2);

        // Assert
        assertThat(result).isEmpty();
    }

    @Test
    public void testFindAllByOrderByIncomeAsc() {
        // Act
        List<Company> result = companyRepository.findAllByOrderByIncomeAsc();

        // Assert
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("Company A");
        assertThat(result.get(1).getName()).isEqualTo("Company B");
    }

    @Test
    public void testFindAllByOrderByIncomeDesc() {
        // Act
        List<Company> result = companyRepository.findAllByOrderByIncomeDesc();

        // Assert
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("Company B");
        assertThat(result.get(1).getName()).isEqualTo("Company A");
    }

    @Test
    public void testFindByIncomeRange() {
        // Act
        List<Company> result = companyRepository.findByIncomeRange(10000, 20000);

        // Assert
        assertThat(result).hasSize(1);
        assertThat(result.get(0).getName()).isEqualTo("Company A");
    }
}