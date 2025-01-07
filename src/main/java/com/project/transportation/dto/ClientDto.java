package com.project.transportation.dto;

import jakarta.validation.constraints.NotBlank;

public record ClientDto(
        Integer id,
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Email is required")
        String email,
        @NotBlank(message = "Phone number is required")
        String phone
) {}
