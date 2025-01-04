package com.project.transportation.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record CreateTransportationDto(
        @NotBlank String startDestination,
        @NotBlank String endDestination,
        LocalDateTime startDate,
        @NotBlank Integer companyId,
        @NotBlank Integer transportableId,
        @NotBlank Integer clientId,
        @NotBlank Integer driverId,
        @NotBlank boolean isPaid
) {
}
