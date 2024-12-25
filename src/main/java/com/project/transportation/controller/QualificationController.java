package com.project.transportation.controller;

import com.project.transportation.dto.QualificationDto;
import com.project.transportation.service.QualificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/qualification")
public class QualificationController {

    private final QualificationService qualificationService;

    QualificationController(QualificationService qualificationService) {
        this.qualificationService = qualificationService;
    }

    // Get qualification by ID
    @GetMapping("/find/{id}")
    public ResponseEntity<QualificationDto> getQualificationById(@PathVariable("id") Integer id) {
        QualificationDto qualification = qualificationService.getQualificationById(id);
        return new ResponseEntity<>(qualification, HttpStatus.OK);
    }

    // Add a new qualification
    @PostMapping("/add")
    public ResponseEntity<QualificationDto> addQualification(@RequestBody QualificationDto qualificationDto) {
        QualificationDto newQualification = qualificationService.addQualification(qualificationDto);
        return new ResponseEntity<>(newQualification, HttpStatus.CREATED);
    }

    // Update an existing qualification
    @PutMapping("/update")
    public ResponseEntity<QualificationDto> updateQualification(@RequestBody QualificationDto qualificationDto) {
        QualificationDto updatedQualification = qualificationService.updateQualification(qualificationDto);
        return new ResponseEntity<>(updatedQualification, HttpStatus.OK);
    }

    // Delete a qualification by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteQualification(@PathVariable("id") Integer id) {
        qualificationService.deleteQualification(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}