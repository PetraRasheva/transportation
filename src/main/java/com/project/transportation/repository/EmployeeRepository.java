package com.project.transportation.repository;

import com.project.transportation.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findEmployeeById(Integer id);

    void deleteEmployeeById(Integer id);
}
