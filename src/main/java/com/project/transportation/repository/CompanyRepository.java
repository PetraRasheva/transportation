package com.project.transportation.repository;

import com.project.transportation.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Optional<Company> findCompanyById(Integer id);

    void deleteCompanyById(Integer id);

    List<Company> findAllByOrderByIncomeAsc(); // Ascending order

    List<Company> findAllByOrderByIncomeDesc(); // Descending order

    // Custom query to filter companies by income range
    @Query("SELECT c FROM Company c WHERE c.income BETWEEN :minIncome AND :maxIncome")
    List<Company> findByIncomeRange(double minIncome, double maxIncome);
}
