package com.project.transportation.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Transportable extends BaseEntity {
    private String name;

    public Transportable(String name) {
        this.name = name;
    }

    public Transportable() {

    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Abstract method to calculate price
    public abstract double calculatePrice();
}
