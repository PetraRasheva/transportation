package com.project.transportation.dto;

public abstract class TransportableDto {
    private String name;

    public TransportableDto() {
        // Default constructor for frameworks like Jackson
    }

    public TransportableDto(String name) {
        this.name = name;
    }

    // Getter and Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
