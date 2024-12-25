package com.project.transportation.service;

import com.project.transportation.dto.QualificationDto;
import com.project.transportation.model.Qualification;

import java.util.List;
import java.util.Set;

public interface QualificationService {
    QualificationDto addQualification(QualificationDto qualificationDto);

    QualificationDto getQualificationById(Integer id);

    QualificationDto updateQualification(QualificationDto qualificationDto);

    List<QualificationDto> getAllQualifications();

    Set<Qualification> getQualificationsByDriverId(Integer driverId);

    void deleteQualification(Integer id);
}
