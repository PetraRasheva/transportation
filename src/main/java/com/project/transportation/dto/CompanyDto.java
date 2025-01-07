package com.project.transportation.dto;

import jakarta.validation.constraints.NotBlank;

public record CompanyDto(
        Integer id,
        @NotBlank(message = "Name is required")
        String name,
        Double income
) {}
