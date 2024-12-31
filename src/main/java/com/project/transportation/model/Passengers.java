package com.project.transportation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Passengers extends Transportable {
    private int count;
    private double baseFare;

    public Passengers(String name, double baseFare, int count) {
        super(name);
        this.baseFare = baseFare;
        this.count = count;
    }

    public Passengers() {
        super();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(double baseFare) {
        this.baseFare = baseFare;
    }

    @Override
    public double calculatePrice() {
        return count * baseFare;
    }
}