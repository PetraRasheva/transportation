package com.project.transportation.service;

import com.project.transportation.dto.TransportationDto;

public interface TransportationService {
    TransportationDto addTransportation(TransportationDto transportationDto);

    TransportationDto updateTransportation(TransportationDto transportationDto);

    TransportationDto getTransportationById(Integer id);

    void deleteTransportation(Integer id);
}
