package com.project.transportation.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Transportation extends BaseEntity implements Serializable {
    private String startDestination;
    private String endDestination;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private double price;
    private boolean isPaid;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Transportable transportable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", nullable = true)
    private Driver driver;

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

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        this.isPaid = paid;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Transportation {" +
                "\n ID: " + getId() +
                ",\n Start Destination: " + startDestination +
                ",\n End Destination: " + endDestination +
                ",\n Start Date: " + startDate +
                ",\n End Date: " + (endDate != null ? endDate : "Not delivered") +
                ",\n Price: " + price +
                ",\n Transportable: " + (transportable != null ? transportable.toString() : "None") +
                ",\n Company: " + (company != null ? company.getName() : "None") +
                '}';
    }
}

