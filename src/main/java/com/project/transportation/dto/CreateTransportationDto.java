package com.project.transportation.dto;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public record CreateTransportationDto(
        @NotBlank String startDestination,
        @NotBlank String endDestination,
        LocalDateTime startDate,
        Integer companyId,
        Integer transportableId
) {
}
