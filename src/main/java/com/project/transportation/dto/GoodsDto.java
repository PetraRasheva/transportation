package com.project.transportation.dto;

public final class GoodsDto extends TransportableDto {
    private String name;
    private double weight;
    private double pricePerKg;

    public GoodsDto() {
        // Default constructor for frameworks like Jackson
    }

    public GoodsDto(String name, double weight, double pricePerKg) {
        this.name = name;
        this.weight = weight;
        this.pricePerKg = pricePerKg;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
