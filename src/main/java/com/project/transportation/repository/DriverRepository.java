package com.project.transportation.repository;

import com.project.transportation.dto.DriverDto;
import com.project.transportation.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriverRepository extends JpaRepository<Driver, Integer> {
    List<Driver> findAllByOrderBySalaryAsc();

    List<Driver> findAllByOrderBySalaryDesc();

    List<Driver> findBySalaryBetween(double minSalary, double maxSalary);
}
