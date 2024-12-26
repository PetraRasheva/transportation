package com.project.transportation.dto;

public class EmployeeDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private Integer companyId;
    private double salary;

    // Constructor
    public EmployeeDto(Integer id, String firstName, String lastName, String email, Integer companyId, double salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.companyId = companyId;
        this.salary = salary;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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