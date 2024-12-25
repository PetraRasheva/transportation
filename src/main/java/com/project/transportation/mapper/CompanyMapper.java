package com.project.transportation.mapper;

import com.project.transportation.dto.CompanyDto;
import com.project.transportation.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyDto toDto(Company company);
    Company toEntity(CompanyDto companyDto);
    @Mapping(target = "id", ignore = true) // Prevent overwriting ID
    void updateCompanyFromDto(CompanyDto dto, @MappingTarget Company entity);
}
