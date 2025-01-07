package com.project.transportation.dto;

public record GoodsDto(
        String name,
        double weight,
        double pricePerKg
) implements TransportableDto {}

