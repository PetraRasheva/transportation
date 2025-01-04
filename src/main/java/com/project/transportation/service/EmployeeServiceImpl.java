package com.project.transportation.service;

import com.project.transportation.dto.DriverDto;
import com.project.transportation.dto.EmployeeDto;
import com.project.transportation.exception.EmployeeNotFoundException;
import com.project.transportation.mapper.EmployeeMapper;
import com.project.transportation.model.*;
import com.project.transportation.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final DriverService driverService;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, @Qualifier("employeeMapperImpl") EmployeeMapper employeeMapper, DriverService driverService) {
        this.employeeRepository = employeeRepository;        
        this.employeeMapper = employeeMapper;
        this.driverService = driverService;
    }

    @Override
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {

        if (employeeDto instanceof DriverDto driverDto) {
            return driverService.addDriver(driverDto);
        }
        throw new IllegalArgumentException("Unsupported Employee type: " + employeeDto.getClass().getSimpleName());
    }

    @Override
    public EmployeeDto getEmployeeById(Integer id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));
        return employeeMapper.toDto(employee);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        Employee employee = employeeRepository.findById(employeeDto.getId())
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found"));

        // If the DTO is a DriverDto, delegate to the DriverService
        if (employeeDto instanceof DriverDto driverDto) {
            return driverService.updateDriver(driverDto);
        }
        throw new IllegalArgumentException("Unsupported Employee type for update.");
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }
}
