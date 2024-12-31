package com.project.transportation.mapper;

import com.project.transportation.dto.VehicleDto;
import com.project.transportation.model.Vehicle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface VehicleMapper {
    @Mapping(source = "company.id", target = "companyId")
    VehicleDto toDto(Vehicle vehicle);
    Vehicle toEntity(VehicleDto vehicleDto);
    @Mapping(target = "id", ignore = true) // Prevent overwriting ID
    @Mapping(target = "company", ignore = true) // Prevent overwriting related entities
    void updateVehicleFromDto(VehicleDto dto, @MappingTarget Vehicle entity);
}
