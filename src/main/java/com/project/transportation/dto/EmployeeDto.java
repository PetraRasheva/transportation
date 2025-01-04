package com.project.transportation.dto;

public class EmployeeDto {
    private Integer id;
    private String name;
    private String email;
    private Integer companyId;
    private double salary;

    // Constructor
    public EmployeeDto(Integer id, String firstName, String email, Integer companyId, double salary) {
        this.id = id;
        this.name = firstName;
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