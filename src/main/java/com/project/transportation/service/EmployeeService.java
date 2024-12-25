package com.project.transportation.service;

import com.project.transportation.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    EmployeeDto addEmployee(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Integer id);

    EmployeeDto updateEmployee(EmployeeDto employee);

    List<EmployeeDto> getAllEmployees();

    void deleteEmployee(Integer id);
}
