package com.project.transportation.controller;

import com.project.transportation.dto.CreateTransportationDto;
import com.project.transportation.dto.TransportationDto;
import com.project.transportation.service.TransportationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<TransportationDto> addTransportation(@RequestBody CreateTransportationDto transportation) {
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

    @GetMapping("/sortByEndDestination")
    public List<TransportationDto> getTransportationsSortedByEndDestination(
            @RequestParam(defaultValue = "true") boolean ascending) {
        return transportationService.getTransportationsSortedByEndDestination(ascending);
    }

    @GetMapping("/filterByEndDestination")
    public List<TransportationDto> getTransportationsByEndDestination(@RequestParam String endDestination) {
        return transportationService.getTransportationsByEndDestination(endDestination);
    }

    @PostMapping("/save/{id}")
    public ResponseEntity<String> saveTransportationToFile(@PathVariable("id") Integer id) {
        try {
            transportationService.saveTransportationToFile(id);
            return new ResponseEntity<>("Transportation saved to file successfully.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to save transportation to file: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getTotalTransportationCount() {
        long count = transportationService.getTotalTransportationCount();
        return ResponseEntity.ok(count);
    }

    @GetMapping("/totalPrice")
    public ResponseEntity<Double> getTotalPrice() {
        Double totalPrice = transportationService.getTotalPrice();
        return ResponseEntity.ok(totalPrice);
    }
}
