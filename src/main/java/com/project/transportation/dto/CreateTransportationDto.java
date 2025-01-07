package com.project.transportation.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record CreateTransportationDto(
        @NotBlank String startDestination,
        @NotBlank String endDestination,
        LocalDateTime startDate,
        @NotBlank Integer companyId,
        @NotBlank TransportableDto transportable,
        @NotBlank String transportableName,
        double weight,  // For Goods
        double pricePerKg,  // For Goods
        double baseFare,  // For Passengers
        int passengerCount,  // For Passengers
        @NotBlank Integer clientId,
        @NotBlank Integer driverId,
        @NotBlank boolean isPaid
) {
}
