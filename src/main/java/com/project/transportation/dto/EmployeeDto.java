package com.project.transportation.dto;

public class EmployeeDto {
    private final Integer id;
    private final String name;
    private final String email;
    private final Integer companyId;
    private final double salary;

    public EmployeeDto(Integer id, String name, String email, Integer companyId, double salary) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.companyId = companyId;
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public double getSalary() {
        return salary;
    }
}