package com.project.transportation.dto;

public record PassengersDto(
        String name,
        double baseFare,
        int count
) implements TransportableDto {}