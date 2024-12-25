package com.project.transportation.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Driver extends Employee {

    @OneToOne
    private Vehicle vehicle;

    @ManyToMany
    private Set<Qualification> qualifications = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Set<Qualification> getQualifications() {
        return qualifications;
    }

    public void setQualifications(Set<Qualification> qualifications) {
        this.qualifications = qualifications;
    }

    @Override
    public Company getCompany() {
        return company;
    }

    @Override
    public void setCompany(Company company) {
        this.company = company;
    }
}
