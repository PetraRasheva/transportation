package com.project.transportation.mapper;

import com.project.transportation.dto.DriverDto;
import com.project.transportation.model.Driver;
import com.project.transportation.model.Qualification;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DriverMapper extends EmployeeMapper{
    @Mapping(target = "vehicle.id", source = "vehicleId") // Mapping vehicleId to vehicle entity
    @Mapping(target = "qualifications", ignore = true)
    Driver toEntity(DriverDto driverDto);

    // Mapping Driver entity to DriverDto
    @Mapping(target = "vehicleId", source = "vehicle.id") // Mapping vehicle entity to vehicleId in DriverDto
    @Mapping(target = "qualificationIds", source = "qualifications") // Map qualifications to qualificationIds
    @Mapping(target = "companyId", source = "company.id") // Mapping company to companyId
    DriverDto toDto(Driver driver);


    // Update Driver entity from DriverDto
    @Mapping(target = "vehicle", ignore = true)
    @Mapping(target = "qualifications", ignore = true)
    @Mapping(target = "company", ignore = true)
    void updateEntityFromDto(DriverDto dto, @MappingTarget Driver entity);

    default Set<Integer> mapQualificationsToIds(Set<Qualification> qualifications) {
        if (qualifications == null) {
            return null;
        }
        return qualifications.stream()
                .map(Qualification::getId)
                .collect(Collectors.toSet());
    }
}
