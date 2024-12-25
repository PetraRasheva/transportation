package com.project.transportation.service;

import com.project.transportation.dto.DriverDto;

import java.util.List;

public interface DriverService {
    DriverDto addDriver(DriverDto driverDto);

    DriverDto getDriverById(Integer id);

    DriverDto updateDriver(DriverDto driverDto);

    List<DriverDto> getAllDrivers();

    void deleteDriver(Integer id);
}
