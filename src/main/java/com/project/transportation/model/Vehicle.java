package com.project.transportation.model;

import jakarta.persistence.*;

@Entity
public class Vehicle extends BaseEntity {

    @Enumerated(EnumType.STRING)
   private VehicleType vehicleType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
