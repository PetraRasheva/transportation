package com.project.transportation.repository;

import com.project.transportation.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Optional<Company> findCompanyById(Integer id);

    void deleteCompanyById(Integer id);
}
