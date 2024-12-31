package com.project.transportation.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Driver extends Employee {
    private int totalQualificationPoints;

    @OneToOne
    private Vehicle vehicle;

    @ManyToMany
    private Set<Qualification> qualifications = new HashSet<>();

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

    public void addQualification(Qualification qualification) {
        qualifications.add(qualification);
    }

    public int getTotalQualificationPoints() {
        return totalQualificationPoints;
    }

    public void setTotalQualificationPoints(int totalQualificationPoints) {
        this.totalQualificationPoints = totalQualificationPoints;
    }
}
