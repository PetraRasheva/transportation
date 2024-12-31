package com.project.transportation.dto;

public record PassengersDto(String name, int passengerCount, double pricePerPassenger) implements TransportableDto {}
