package com.project.transportation.dto;

public class PassengersDto extends TransportableDto {
    private int passengerCount;
    private double pricePerPassenger;

    public PassengersDto() {
    }

    public PassengersDto(String name, int passengerCount, double pricePerPassenger) {
        super(name);
        this.passengerCount = passengerCount;
        this.pricePerPassenger = pricePerPassenger;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }

    public double getPricePerPassenger() {
        return pricePerPassenger;
    }

    public void setPricePerPassenger(double pricePerPassenger) {
        this.pricePerPassenger = pricePerPassenger;
    }
}