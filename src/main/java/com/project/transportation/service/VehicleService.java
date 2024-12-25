package com.project.transportation.service;

import com.project.transportation.dto.VehicleDto;

import java.util.List;

public interface VehicleService {
    VehicleDto addVehicle(VehicleDto vehicleDto);

    VehicleDto getVehicleById(Integer id);

    VehicleDto updateVehicle(VehicleDto vehicle);

    List<VehicleDto> getAllVehicles();

    void deleteVehicle(Integer id);
}
