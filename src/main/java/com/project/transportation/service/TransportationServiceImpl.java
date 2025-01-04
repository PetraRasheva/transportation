package com.project.transportation.service;

import com.project.transportation.dto.CreateTransportationDto;
import com.project.transportation.dto.TransportationDto;
import com.project.transportation.exception.CompanyNotFoundException;
import com.project.transportation.exception.TransportationNotFoundException;
import com.project.transportation.mapper.TransportationMapper;
import com.project.transportation.model.Company;
import com.project.transportation.model.Transportable;
import com.project.transportation.model.Transportation;
import com.project.transportation.repository.CompanyRepository;
import com.project.transportation.repository.TransportableRepository;
import com.project.transportation.repository.TransportationRepository;
import com.project.transportation.utils.FileUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransportationServiceImpl implements TransportationService {
    private final TransportationRepository transportationRepository;
    private final CompanyRepository companyRepository;
    private final TransportationMapper transportationMapper;
    private final TransportableRepository transportableRepository;

    private final String FILE_NAME = "transportations.ser"; // File to store serialized objects

    public TransportationServiceImpl(TransportationRepository transportationRepository, TransportationMapper transportationMapper, CompanyRepository companyRepository, TransportableRepository transportableRepository) {
        this.transportationRepository = transportationRepository;
        this.companyRepository = companyRepository;
        this.transportationMapper = transportationMapper;
        this.transportableRepository = transportableRepository;
    }

    @Override
    public TransportationDto addTransportation(CreateTransportationDto transportationDto) {
        Company company = companyRepository.findById(transportationDto.companyId())
                .orElseThrow(() -> new CompanyNotFoundException("Company with id " + transportationDto.companyId() + " was not found"));

       Transportation transportation = transportationMapper.toEntity(transportationDto);
       transportation.setCompany(company);

       Transportable transportable = transportableRepository.findById(transportationDto.transportableId())
               .orElseThrow(() -> new TransportationNotFoundException("Transportable with id " + transportationDto.transportableId() + " was not found"));

        transportation.setTransportable(transportable);
        transportation.setPrice();
        //transportation.setPaid(transportationDto.isPaid());

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
        Transportation transportation = transportationRepository.findTransportationById(id).orElseThrow(() -> new TransportationNotFoundException("Transportation with id " + id + " was not found"));
        return transportationMapper.toDto(transportation);
    }

    @Override
    public void deleteTransportation(Integer id) {

    }

    @Override
    public List<TransportationDto> getTransportationsSortedByEndDestination(boolean ascending) {
        List<Transportation> transportations;

        if (ascending) {
            transportations = transportationRepository.findAllByOrderByEndDestinationAsc();
        } else {
            transportations = transportationRepository.findAllByOrderByEndDestinationDesc();
        }

        // Map entities to DTOs
        return transportations.stream()
                .map(transportationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransportationDto> getTransportationsByEndDestination(String endDestination) {
        // Fetch transportations from the repository
        List<Transportation> transportations = transportationRepository.findAllByEndDestination(endDestination);

        // Map entities to DTOs
        return transportations.stream()
                .map(transportationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void saveTransportationToFile(Integer id) {
        // Fetch the Transportation object
        Transportation transportation = transportationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transportation not found with ID: " + id));

        // Generate the filename based on the ID
        String filename = "trans_" + transportation.getId() + ".txt";

        // Save to file using utility
        FileUtils.writeToFile(transportation.toString(), filename);
    }

    @Override
    public double getTotalPrice() {
        return transportationRepository.getTotalPriceOfAllTransportations();
    }

    @Override
    public long getTotalTransportationCount() {
        return transportationRepository.count();  // Get the count of all transportations
    }

    private double calculateDistance(TransportationDto transportationDto) {
        // SOME DISTANCE CALCULATION LOGIC
        return 1 + (Math.random() * (100 - 1));
    }
}
