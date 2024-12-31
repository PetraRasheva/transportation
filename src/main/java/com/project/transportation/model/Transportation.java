package com.project.transportation.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Transportation extends BaseEntity {
    private String startDestination;
    private String endDestination;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double price;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transportable_id")
    private Transportable transportable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getStartDestination() {
        return startDestination;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice() {
        if (transportable != null) {
            this.price = transportable.calculatePrice();
        } else {
            throw new IllegalStateException("Transportable object is required to calculate price.");
        }
    }

    public void setStartDestination(String startDestination) {
        this.startDestination = startDestination;
    }

    public String getEndDestination() {
        return endDestination;
    }

    public void setEndDestination(String endDestination) {
        this.endDestination = endDestination;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Transportable getTransportable() {
        return transportable;
    }

    public void setTransportable(Transportable transportable) {
        this.transportable = transportable;
    }
}

