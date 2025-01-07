package com.project.transportation.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record TransportationDto(
        @NotBlank Integer id,
        @NotBlank String startDestination,
        @NotBlank String endDestination,
        @NotBlank LocalDateTime startDate,
        LocalDateTime endDate,
        @NotBlank Integer companyId,
        TransportableDto transportableDto,
        @NotBlank Integer clientId,
        @NotBlank Integer driverId,
        @NotBlank Double price,
        @NotBlank boolean isPaid
) {}
