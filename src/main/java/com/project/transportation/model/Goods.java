package com.project.transportation.model;

import jakarta.persistence.Entity;

import java.io.Serializable;

@Entity
public class Goods extends Transportable {
    private double weight;
    private double pricePerKg;

    public Goods(String name, double weight, double pricePerKg) {
        super(name); // Assuming the Transportable class has a constructor with name
        this.weight = weight;
        this.pricePerKg = pricePerKg;
    }

    public Goods() {
        super();
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getPricePerKg() {
        return pricePerKg;
    }

    public void setPricePerKg(double pricePerKg) {
        this.pricePerKg = pricePerKg;
    }

    @Override
    public double calculatePrice() {
        return weight * pricePerKg;
    }

    @Override
    public String toString() {
        return "Goods { Name: " + getName() +
                ", Weight: " + weight +
                ", Price Per Kg: " + pricePerKg +
                " }";
    }
}
