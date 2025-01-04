package com.project.transportation.mapper;

import com.project.transportation.dto.*;
import com.project.transportation.model.Transportation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TransportationMapper {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "transportable.id", target = "transportableId")
    @Mapping(source = "driver.id", target = "driverId")
    @Mapping(source = "paid", target = "isPaid")
    TransportationDto toDto(Transportation transportation);

    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "driverId", target = "driver.id")
    @Mapping(source = "isPaid", target = "paid")
    Transportation toEntity(CreateTransportationDto createTransportationDto);

    @Mapping(target = "id", ignore = true) // Prevent overwriting ID
    @Mapping(target = "company", ignore = true) // Prevent overwriting related entities
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "driver", ignore = true)
    void updateTransportationFromDto(TransportationDto dto, @MappingTarget Transportation entity);
}
