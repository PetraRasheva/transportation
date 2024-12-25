package com.project.transportation.mapper;

import com.project.transportation.dto.TransportationDto;
import com.project.transportation.model.Transportation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TransportationMapper {
    TransportationDto toDto(Transportation transportation);
    Transportation toEntity(TransportationDto transportationDto);
    @Mapping(target = "id", ignore = true) // Prevent overwriting ID
    @Mapping(target = "company", ignore = true) // Prevent overwriting related entities
    void updateTransportationFromDto(TransportationDto dto, @MappingTarget Transportation entity);
}
