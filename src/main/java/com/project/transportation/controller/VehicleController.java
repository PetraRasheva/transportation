package com.project.transportation.controller;

import com.project.transportation.dto.VehicleDto;
import com.project.transportation.service.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<VehicleDto> getVehicleById(@PathVariable("id") Integer id) {
        VehicleDto vehicle = vehicleService.getVehicleById(id);
        return new ResponseEntity<>(vehicle, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<VehicleDto> addVehicle(@RequestBody VehicleDto vehicle) {
        VehicleDto newVehicle = vehicleService.addVehicle(vehicle);
        return new ResponseEntity<>(newVehicle, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<VehicleDto> updateVehicle(@RequestBody VehicleDto vehicle) {
        VehicleDto updateVehicle = vehicleService.updateVehicle(vehicle);
        return new ResponseEntity<>(updateVehicle, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable("id") Integer id) {
        vehicleService.deleteVehicle(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

