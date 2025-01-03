package com.project.transportation.controller;

import com.project.transportation.dto.TransportationDto;
import com.project.transportation.service.TransportationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transportation")

public class TransportationController {

    private final TransportationService transportationService;

    public TransportationController(TransportationService transportationService) {
        this.transportationService = transportationService;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<TransportationDto> getTransportationById(@PathVariable("id") Integer id) {
        TransportationDto transportation = transportationService.getTransportationById(id);
        return new ResponseEntity<>(transportation, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<TransportationDto> addTransportation(@RequestBody TransportationDto transportation) {
        TransportationDto newTransportation = transportationService.addTransportation(transportation);
        return new ResponseEntity<>(newTransportation, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<TransportationDto> updateTransportation(@RequestBody TransportationDto transportation) {
        TransportationDto updateTransportation = transportationService.updateTransportation(transportation);
        return new ResponseEntity<>(updateTransportation, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTransportation(@PathVariable("id") Integer id) {
        transportationService.deleteTransportation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
