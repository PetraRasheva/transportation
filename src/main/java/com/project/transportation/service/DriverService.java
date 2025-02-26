package com.project.transportation.service;

import com.project.transportation.dto.DriverDto;
import com.project.transportation.dto.DriverWithInfoDto;

import java.util.List;

public interface DriverService {
    DriverDto addDriver(DriverDto driverDto);

    DriverDto getDriverById(Integer id);

    DriverDto updateDriver(DriverDto driverDto);

    List<DriverWithInfoDto> getAllDriversWithStatistics();

    void deleteDriver(Integer id);

    List<DriverDto> getDriversSortedBySalary(boolean ascending);

    List<DriverDto> getDriversBySalaryRange(double minSalary, double maxSalary);

    List<DriverDto> getDriversSortedByQualificationPoints(boolean ascending);

    List<DriverDto> getDriversByQualificationPointsRange(int minPoints, int maxPoints);
}
