package com.project.transportation.service;

import com.project.transportation.dto.DriverDto;
import com.project.transportation.dto.DriverWithInfoDto;
import com.project.transportation.exception.CompanyNotFoundException;
import com.project.transportation.exception.DriverNotFoundException;
import com.project.transportation.exception.QualificationNotFoundException;
import com.project.transportation.exception.VehicleNotFoundException;
import com.project.transportation.mapper.DriverMapper;
import com.project.transportation.model.*;
import com.project.transportation.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;
    private final DriverMapper driverMapper;
    private final CompanyRepository companyRepository;
    private final QualificationRepository qualificationRepository;
    private final TransportationRepository transportationRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository, VehicleRepository vehicleRepository,
                             DriverMapper driverMapper, CompanyRepository companyRepository, QualificationRepository qualificationRepository, TransportationRepository transportationRepository) {
        this.driverRepository = driverRepository;
        this.vehicleRepository = vehicleRepository;
        this.driverMapper = driverMapper;
        this.companyRepository = companyRepository;
        this.qualificationRepository = qualificationRepository;
        this.transportationRepository = transportationRepository;
    }

    @Override
    public DriverDto addDriver(DriverDto driverDto) {
        Company company = companyRepository.findById(driverDto.getCompanyId())
                .orElseThrow(() -> new CompanyNotFoundException("Company not found"));

        Driver driver = driverMapper.toEntity(driverDto);

        driverDto.getQualificationIds().forEach(qualificationId -> {
            Qualification qualification = qualificationRepository.findById(qualificationId)
                    .orElseThrow(() -> new QualificationNotFoundException("Qualification not found for ID: " + qualificationId));
            driver.addQualification(qualification);
        });

        driver.setCompany(company);

        if (driverDto.getVehicleId() != null) {
            Vehicle vehicle = vehicleRepository.findById(driverDto.getVehicleId())
                    .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"));
            driver.setVehicle(vehicle);
        }

        Driver savedDriver = driverRepository.save(driver);
        return driverMapper.toDto(savedDriver);
    }

    @Override
    public DriverDto getDriverById(Integer id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new DriverNotFoundException("Driver not found"));

        return driverMapper.toDto(driver);
    }

    @Override
    public DriverDto updateDriver(DriverDto driverDto) {
        Driver driver = driverRepository.findById(driverDto.getId())
                .orElseThrow(() -> new DriverNotFoundException("Driver not found"));

        driverMapper.updateEntityFromDto(driverDto, driver);

        if (driverDto.getCompanyId() != null) {
            if (driver.getCompany() == null || !driverDto.getCompanyId().equals(driver.getCompany().getId())) {
                Company newCompany = companyRepository.findById(driverDto.getCompanyId())
                        .orElseThrow(() -> new CompanyNotFoundException("Company with id " + driverDto.getCompanyId() + " not found"));
                driver.setCompany(newCompany); // Update the company of the driver
            }
        }

        if (driverDto.getVehicleId() != null) {
            Vehicle vehicle = vehicleRepository.findById(driverDto.getVehicleId())
                    .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"));
            driver.setVehicle(vehicle);
        }

        driver.getQualifications().clear();
        driverDto.getQualificationIds().forEach(qualificationId -> {
            Qualification qualification = qualificationRepository.findById(qualificationId)
                    .orElseThrow(() -> new QualificationNotFoundException("Qualification not found for ID: " + qualificationId));
            driver.addQualification(qualification);
        });

        //updateDriverQualifications(driver, driverDto.getQualificationIds());

        Driver updatedDriver = driverRepository.save(driver);
        return driverMapper.toDto(updatedDriver);
    }

    public List<DriverWithInfoDto> getAllDriversWithStatistics() {
        List<Driver> drivers = driverRepository.findAll();

        // Map the list of drivers to a list of DriverWithCountDto
        return drivers.stream()
                .map(driver -> new DriverWithInfoDto(
                        driver.getId(),
                        driver.getName(),
                        driver.getTransportations().size(), // Calculate size of transportations set
                        calculateDriverIncome(driver.getTransportations())
                ))
                .collect(Collectors.toList());
    }

    private double calculateDriverIncome(Set<Transportation> transportations) {
        return transportations.stream()
                .mapToDouble(Transportation::getPrice)  // Get the price of each transportation
                .sum();
    }

    @Override
    public void deleteDriver(Integer id) {
        Driver driver = driverRepository.findById(id)
                .orElseThrow(() -> new DriverNotFoundException("Driver not found"));

        // Remove the driver reference from all related transportations
        driver.getTransportations().forEach(transportation -> {
            transportation.setDriver(null);  // Ensure driver reference is null
            transportationRepository.save(transportation);  // Persist the changes
        });

        driverRepository.delete(driver);
    }

    @Override
    public List<DriverDto> getDriversSortedBySalary(boolean ascending) {
        List<Driver> drivers;
        if (ascending) {
            drivers = driverRepository.findAllByOrderBySalaryAsc();
        } else {
            drivers = driverRepository.findAllByOrderBySalaryDesc();
        }
        return drivers.stream()
                .map(driverMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DriverDto> getDriversBySalaryRange(double minSalary, double maxSalary) {
        List<Driver> drivers = driverRepository.findBySalaryBetween(minSalary, maxSalary);
        return drivers.stream()
                .map(driverMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<DriverDto> getDriversSortedByQualificationPoints(boolean ascending) {
        // Fetch all drivers from the repository
        List<Driver> drivers = driverRepository.findAll();

        // Convert Driver entities to DriverDto using the mapper
        List<DriverDto> driverDtos = drivers.stream()
                .map(driver -> driverMapper.toDto(driver))  // Assuming driverMapper correctly converts Driver to DriverDto
                .collect(Collectors.toList());

        // Sort the driverDtos based on the total qualification points (already stored in DriverDto)
        return driverDtos.stream()
                .sorted(ascending
                        ? Comparator.comparingInt(DriverDto::getTotalQualificationPoints)  // Ascending order
                        : Comparator.comparingInt(DriverDto::getTotalQualificationPoints).reversed())  // Descending order
                .collect(Collectors.toList());
    }

    @Override
    public List<DriverDto> getDriversByQualificationPointsRange(int minPoints, int maxPoints) {
        // Fetch all drivers from the repository
        List<Driver> drivers = driverRepository.findAll();

        // Convert Driver entities to DriverDto using the mapper
        List<DriverDto> driverDtos = drivers.stream()
                .map(driverMapper::toDto)
                .collect(Collectors.toList());

        // Filter the list based on the qualification points range
        return driverDtos.stream()
                .filter(driver -> {
                    int totalPoints = driver.getTotalQualificationPoints();
                    return totalPoints >= minPoints && totalPoints <= maxPoints;
                })
                .collect(Collectors.toList());
    }

//    private void updateDriverQualifications(Driver driver, Set<Integer> qualificationIds) {
//        if (qualificationIds == null || qualificationIds.isEmpty()) {
//            // Clear existing qualifications
//            for (Qualification qualification : driver.getQualifications()) {
//                qualification.getDrivers().remove(driver);
//            }
//            driver.getQualifications().clear();
//            return;
//        }
//
//        // Fetch new qualifications
//        Set<Qualification> newQualifications = qualificationIds.stream()
//                .map(id -> qualificationRepository.findById(id)
//                        .orElseThrow(() -> new RuntimeException("Qualification with id " + id + " not found")))
//                .collect(Collectors.toSet());
//
//        // Remove the driver from old qualifications
//        for (Qualification qualification : driver.getQualifications()) {
//            qualification.getDrivers().remove(driver);
//        }
//
//        // Clear current qualifications and set new ones
//        driver.getQualifications().clear();
//        driver.getQualifications().addAll(newQualifications);
//
//        // Add the driver to the new qualifications
//        for (Qualification qualification : newQualifications) {
//            qualification.getDrivers().add(driver);
//        }
//    }
}
