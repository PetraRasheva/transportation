package com.project.transportation.mapper;

import com.project.transportation.dto.ClientDto;
import com.project.transportation.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDto toDto(Client client);
    Client toEntity(ClientDto clientDto);

    @Mapping(target = "id", ignore = true) // Prevent overwriting ID
    void updateClientFromDto(ClientDto clientDto, @MappingTarget Client client);
}
