package com.project.transportation.service;

import com.project.transportation.dto.VehicleDto;
import com.project.transportation.exception.CompanyNotFoundException;
import com.project.transportation.exception.VehicleNotFoundException;
import com.project.transportation.mapper.VehicleMapper;
import com.project.transportation.model.Company;
import com.project.transportation.model.Vehicle;
import com.project.transportation.repository.CompanyRepository;
import com.project.transportation.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {
    
    private final VehicleRepository vehicleRepository;
    private final VehicleMapper vehicleMapper;
    private final CompanyRepository companyRepository;
    
    public VehicleServiceImpl(VehicleRepository vehicleRepository, VehicleMapper vehicleMapper, CompanyRepository companyRepository) {
        this.vehicleRepository = vehicleRepository;
        this.vehicleMapper = vehicleMapper;
        this.companyRepository = companyRepository;
    }
    @Override
    public VehicleDto addVehicle(VehicleDto vehicleDto) {
        Company company = companyRepository.findById(vehicleDto.companyId())
                .orElseThrow(() -> new CompanyNotFoundException("Company not found"));

        // Convert VehicleDto to Vehicle entity
        Vehicle vehicle = vehicleMapper.toEntity(vehicleDto);

        // Set the company to the vehicle
        vehicle.setCompany(company);

        // Save the vehicle entity
        vehicle = vehicleRepository.save(vehicle);

        // Return the saved vehicle as DTO
        return vehicleMapper.toDto(vehicle);
    }

    @Override
    public VehicleDto getVehicleById(Integer id) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        return vehicleMapper.toDto(vehicle);
    }

    @Override
    public VehicleDto updateVehicle(VehicleDto vehicleDto) {
        Vehicle vehicle = vehicleRepository.findVehicleById(vehicleDto.id())
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle with id " + vehicleDto.id() + " was not found"));

        vehicleMapper.updateVehicleFromDto(vehicleDto, vehicle);
        return vehicleMapper.toDto(vehicleRepository.save(vehicle));
    }

    @Override
    public void deleteVehicle(Integer id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public List<VehicleDto> getAllVehicles() {
        return vehicleRepository.findAll().stream()
                .map(vehicleMapper::toDto)
                .collect(Collectors.toList());
    }
}
