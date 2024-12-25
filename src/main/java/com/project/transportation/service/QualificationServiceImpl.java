package com.project.transportation.service;

import com.project.transportation.dto.QualificationDto;
import com.project.transportation.exception.DriverNotFoundException;
import com.project.transportation.exception.QualificationNotFoundException;
import com.project.transportation.mapper.QualificationMapper;
import com.project.transportation.model.Driver;
import com.project.transportation.model.Qualification;
import com.project.transportation.repository.DriverRepository;
import com.project.transportation.repository.QualificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QualificationServiceImpl implements QualificationService {

    private final QualificationRepository qualificationRepository;
    private final QualificationMapper qualificationMapper;
    private final DriverRepository driverRepository;

    public QualificationServiceImpl(QualificationRepository qualificationRepository, QualificationMapper qualificationMapper, DriverRepository driverRepository) {
        this.qualificationRepository = qualificationRepository;
        this.qualificationMapper = qualificationMapper;
        this.driverRepository = driverRepository;
    }

    @Override
    public QualificationDto addQualification(QualificationDto qualificationDto) {
        // Convert DTO to entity
        Qualification qualification = qualificationMapper.toEntity(qualificationDto);

        // Save qualification entity in the database
        Qualification savedQualification = qualificationRepository.save(qualification);

        // Return saved qualification as DTO
        return qualificationMapper.toDto(savedQualification);
    }

    @Override
    public QualificationDto getQualificationById(Integer id) {
        // Find the qualification by ID from the repository
        Qualification qualification = qualificationRepository.findById(id)
                .orElseThrow(() -> new QualificationNotFoundException("Qualification not found for id: " + id));

        // Convert entity to DTO and return
        return qualificationMapper.toDto(qualification);
    }

    @Override
    public QualificationDto updateQualification(QualificationDto qualificationDto) {
        // Find the qualification by ID from the repository
        Qualification qualification = qualificationRepository.findById(qualificationDto.id())
                .orElseThrow(() -> new QualificationNotFoundException("Qualification not found for id: " + qualificationDto.id()));

        // Update the qualification entity fields
        qualification.setName(qualificationDto.name());

        // Save the updated entity and return the updated DTO
        Qualification updatedQualification = qualificationRepository.save(qualification);
        return qualificationMapper.toDto(updatedQualification);
    }

    @Override
    public List<QualificationDto> getAllQualifications() {
        List<Qualification> qualifications = qualificationRepository.findAll();

        return qualifications.stream()
                .map(qualificationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteQualification(Integer id) {
        // Check if the qualification exists before deleting
        if (!qualificationRepository.existsById(id)) {
            throw new QualificationNotFoundException("Qualification not found for id: " + id);
        }

        // Delete the qualification by ID
        qualificationRepository.deleteById(id);
    }

    @Override
    public Set<Qualification> getQualificationsByDriverId(Integer driverId) {
        Driver driver = driverRepository.findById(driverId)
                .orElseThrow(() -> new DriverNotFoundException("Driver not found for id: " + driverId));

        // Fetch qualifications by driver ID
        return qualificationRepository.findAllByDriverId(driver);
    }
}
