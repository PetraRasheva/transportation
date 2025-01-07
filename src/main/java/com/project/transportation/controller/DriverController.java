package com.project.transportation.controller;

import com.project.transportation.dto.DriverDto;
import com.project.transportation.dto.DriverWithInfoDto;
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteDriver(@PathVariable("id") Integer id) {
        driverService.deleteDriver(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/sorted-by-salary")
    public List<DriverDto> getDriversSortedBySalary(@RequestParam(defaultValue = "true") boolean ascending) {
        return driverService.getDriversSortedBySalary(ascending);
    }

    @GetMapping("/filter-by-salary-range")
    public List<DriverDto> getDriversBySalaryRange(@RequestParam double min, @RequestParam double max) {
        return driverService.getDriversBySalaryRange(min, max);
    }

    @GetMapping("/sorted-by-qualification-points")
    public List<DriverDto> getDriversSortedByQualificationPoints(@RequestParam(defaultValue = "true") boolean ascending) {
        return driverService.getDriversSortedByQualificationPoints(ascending);
    }

    @GetMapping("/filter-by-qualification-points-range")
    public List<DriverDto> getDriversByQualificationPointsRange(@RequestParam int min, @RequestParam int max) {
        return driverService.getDriversByQualificationPointsRange(min, max);
    }

    @GetMapping("/with-statistics")
    public ResponseEntity<List<DriverWithInfoDto>> getAllDriversWithStatistics() {
        List<DriverWithInfoDto> drivers = driverService.getAllDriversWithStatistics();
        return ResponseEntity.ok(drivers);
    }
}
