package com.project.transportation.dto;

import com.project.transportation.model.TransportationType;

import java.time.LocalDateTime;

public record TransportationDto(Integer id, String startDestination, String endDestination, LocalDateTime startDate, LocalDateTime endDate, TransportationType transportationType, Integer companyId, Double price) {
}
