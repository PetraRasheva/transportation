package com.project.transportation.service;

import com.project.transportation.dto.TransportationDto;
import com.project.transportation.exception.CompanyNotFoundException;
import com.project.transportation.exception.TransportationNotFoundException;
import com.project.transportation.mapper.TransportationMapper;
import com.project.transportation.model.Company;
import com.project.transportation.model.Transportation;
import com.project.transportation.repository.CompanyRepository;
import com.project.transportation.repository.TransportationRepository;
import org.springframework.stereotype.Service;

@Service
public class TransportationServiceImpl implements TransportationService {
    private final TransportationRepository transportationRepository;
    private final CompanyRepository companyRepository;
    private final TransportationMapper transportationMapper;

    public TransportationServiceImpl(TransportationRepository transportationRepository, TransportationMapper transportationMapper, CompanyRepository companyRepository) {
        this.transportationRepository = transportationRepository;
        this.companyRepository = companyRepository;
        this.transportationMapper = transportationMapper;
    }

    @Override
    public TransportationDto addTransportation(TransportationDto transportationDto) {
        Company company = companyRepository.findById(transportationDto.companyId())
                .orElseThrow(() -> new CompanyNotFoundException("Company with id " + transportationDto.companyId() + " was not found"));

       Transportation transportation = transportationMapper.toEntity(transportationDto);
       transportation.setCompany(company);

       Transportation savedTransportation = transportationRepository.save(transportation);
       return transportationMapper.toDto(savedTransportation);
    }

    @Override
    public TransportationDto updateTransportation(TransportationDto transportationDto) {
        Transportation transportation = transportationRepository.findTransportationById(transportationDto.id())
                .orElseThrow(() -> new TransportationNotFoundException("Transportation with id " + transportationDto.id() + " was not found"));

        transportationMapper.updateTransportationFromDto(transportationDto, transportation);

        Transportation savedTransportation = transportationRepository.save(transportation);
        return transportationMapper.toDto(savedTransportation);
    }

    @Override
    public TransportationDto getTransportationById(Integer id) {
        return null;
    }

    @Override
    public void deleteTransportation(Integer id) {

    }

//    @Override
//    public TransportationDto createTransportationForCompany(Integer companyId, TransportationDto transportationDto) {
//        Company company = companyRepository.findById(companyId)
//                .orElseThrow(() -> new RuntimeException("Company not found"));
//
//        // Присвояваме компанията на превоза
//        transportation.setCompany(company);
//
//        // Записваме новия превоз в базата данни
//        return transportationRepository.save(transportation);
//    }


}
