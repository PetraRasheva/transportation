package com.project.transportation.mapper;

import com.project.transportation.dto.EmployeeDto;
import com.project.transportation.model.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.context.annotation.Primary;

@Mapper(componentModel = "spring")
@Primary
public interface EmployeeMapper {
    // Mapping common Employee fields to EmployeeDto
    EmployeeDto toDto(Employee employee);

    // Update Employee entity from EmployeeDto (ignoring company field)
    @Mapping(target = "company", ignore = true)
    void updateEmployeeFromDto(EmployeeDto dto, @MappingTarget Employee entity);

    // Update Employee entity from EmployeeDto (ignoring ID and company field)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "company", ignore = true)
    void updateEntityFromDto(EmployeeDto dto, @MappingTarget Employee entity);
}
