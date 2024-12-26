package com.project.transportation.controller;

import com.project.transportation.dto.DriverDto;
import com.project.transportation.service.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<DriverDto> getDriverById(@PathVariable("id") Integer id) {
        DriverDto driver = driverService.getDriverById(id);
        return new ResponseEntity<>(driver, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<DriverDto> addDriver(@RequestBody DriverDto driverDto) {
        DriverDto newDriver = driverService.addDriver(driverDto);
        return new ResponseEntity<>(newDriver, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<DriverDto> updateDriver(@RequestBody DriverDto driverDto) {
        DriverDto updatedDriver = driverService.updateDriver(driverDto);
        return new ResponseEntity<>(updatedDriver, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DriverDto>> getAllDrivers() {
        List<DriverDto> drivers = driverService.getAllDrivers();
        return new ResponseEntity<>(drivers, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable("id") Integer id) {
        driverService.deleteDriver(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/sortedBySalary")
    public List<DriverDto> getDriversSortedBySalary(@RequestParam(defaultValue = "true") boolean ascending) {
        return driverService.getDriversSortedBySalary(ascending);
    }

    @GetMapping("/filterBySalaryRange")
    public List<DriverDto> getDriversBySalaryRange(@RequestParam double minSalary, @RequestParam double maxSalary) {
        return driverService.getDriversBySalaryRange(minSalary, maxSalary);
    }

    @GetMapping("/sortedByQualificationPoints")
    public List<DriverDto> getDriversSortedByQualificationPoints(@RequestParam(defaultValue = "true") boolean ascending) {
        return driverService.getDriversSortedByQualificationPoints(ascending);
    }

    @GetMapping("/filterByQualificationPointsRange")
    public List<DriverDto> getDriversByQualificationPointsRange(@RequestParam int minPoints, @RequestParam int maxPoints) {
        return driverService.getDriversByQualificationPointsRange(minPoints, maxPoints);
    }
}
