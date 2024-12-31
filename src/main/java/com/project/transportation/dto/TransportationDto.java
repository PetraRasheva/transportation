package com.project.transportation.dto;

import com.project.transportation.model.Transportable;

import java.time.LocalDateTime;

public record TransportationDto(
        Integer id,
        String startDestination,
        String endDestination,
        LocalDateTime startDate,
        LocalDateTime endDate,
        Integer companyId,
        Integer transportableId,
        Double price
) {}
