package com.project.transportation.dto;

public record DriverWithInfoDto(
        Integer id,
        String name,
        int transportationCount,
        double income
) {}
