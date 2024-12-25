package com.project.transportation.dto;

import com.project.transportation.model.VehicleType;

public record VehicleDto(Integer id, VehicleType vehicleType, Integer companyId) {
}
