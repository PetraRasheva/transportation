package com.project.transportation.mapper;

import com.project.transportation.dto.QualificationDto;
import com.project.transportation.model.Qualification;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface QualificationMapper {
    QualificationDto toDto(Qualification qualification);

    Qualification toEntity(QualificationDto qualificationDto);
}
