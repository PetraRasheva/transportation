package com.project.transportation.service;

import com.project.transportation.dto.CreateTransportationDto;
import com.project.transportation.dto.TransportationDto;

import java.util.List;

public interface TransportationService {
    TransportationDto addTransportation(CreateTransportationDto transportationDto);

    TransportationDto updateTransportation(TransportationDto transportationDto);

    TransportationDto getTransportationById(Integer id);

    void deleteTransportation(Integer id);

    List<TransportationDto> getTransportationsSortedByEndDestination(boolean ascending);

    List<TransportationDto> getTransportationsByEndDestination(String endDestination);

    void saveTransportationToFile(Integer id);

    double getTotalPrice();

    long getTotalTransportationCount();
}
