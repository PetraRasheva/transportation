package com.project.transportation.service;

import com.project.transportation.dto.*;
import com.project.transportation.exception.ClientNotFoundException;
import com.project.transportation.exception.CompanyNotFoundException;
import com.project.transportation.exception.DriverNotFoundException;
import com.project.transportation.exception.TransportationNotFoundException;
import com.project.transportation.mapper.TransportationMapper;
import com.project.transportation.model.*;
import com.project.transportation.repository.ClientRepository;
import com.project.transportation.repository.CompanyRepository;
import com.project.transportation.repository.DriverRepository;
import com.project.transportation.repository.TransportationRepository;
import com.project.transportation.utils.FileUtils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransportationServiceImpl implements TransportationService {
    private final TransportationRepository transportationRepository;
    private final CompanyRepository companyRepository;
    private final TransportationMapper transportationMapper;
    private final DriverRepository driverRepository;
    private final ClientRepository clientRepository;

    public TransportationServiceImpl(TransportationRepository transportationRepository, TransportationMapper transportationMapper, CompanyRepository companyRepository, DriverRepository driverRepository, ClientRepository clientRepository) {
        this.transportationRepository = transportationRepository;
        this.companyRepository = companyRepository;
        this.transportationMapper = transportationMapper;
        this.driverRepository = driverRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public TransportationDto addTransportation(CreateTransportationDto transportationDto) {
        Company company = companyRepository.findById(transportationDto.companyId())
                .orElseThrow(() -> new CompanyNotFoundException("Company with id " + transportationDto.companyId() + " was not found"));

       Transportation transportation = transportationMapper.toEntity(transportationDto);
       transportation.setCompany(company);

        Transportable transportable = createTransportable(transportationDto.transportable());
        transportation.setTransportable(transportable);

        transportation.setPrice();

        Transportation savedTransportation = transportationRepository.save(transportation);

       return transportationMapper.toDto(savedTransportation);
    }

    @Override
    @Transactional
    public TransportationDto updateTransportation(TransportationDto transportationDto) {
        Transportation transportation = transportationRepository.findTransportationById(transportationDto.id())
                .orElseThrow(() -> new TransportationNotFoundException("Transportation with id " + transportationDto.id() + " was not found"));

        transportationMapper.updateTransportationFromDto(transportationDto, transportation);

        if (transportationDto.driverId() != null) {
            Driver driver = driverRepository.findById(transportationDto.driverId())
                    .orElseThrow(() -> new DriverNotFoundException("Driver with ID " + transportationDto.driverId() + " not found"));
            transportation.setDriver(driver);
        } else {
            transportation.setDriver(null); // Handle nullable driver case
        }

        if (transportationDto.clientId() != null) {
            Client client = clientRepository.findById(transportationDto.clientId())
                    .orElseThrow(() -> new ClientNotFoundException("Client with ID " + transportationDto.clientId() + " not found"));
            transportation.setClient(client);
        } else {
            transportation.setClient(null); // Handle nullable client case
        }

        Transportation savedTransportation = transportationRepository.save(transportation);
        return transportationMapper.toDto(savedTransportation);
    }

    @Override
    public TransportationDto getTransportationById(Integer id) {
        Transportation transportation = transportationRepository.findTransportationById(id).orElseThrow(() -> new TransportationNotFoundException("Transportation with id " + id + " was not found"));
        return transportationMapper.toDto(transportation);
    }

    @Override
    @Transactional
    public void deleteTransportation(Integer id) {
        transportationRepository.deleteById(id);
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
    public double getTotalIncome() {
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

    private Transportable createTransportable(TransportableDto dto) {
        if (dto instanceof GoodsDto goodsDto) {
            return new Goods(goodsDto.name(), goodsDto.weight(), goodsDto.pricePerKg());
        } else if (dto instanceof PassengersDto passengersDto) {
            return new Passengers(passengersDto.name(), passengersDto.baseFare(), passengersDto.count());
        }
        throw new IllegalArgumentException("Unsupported TransportableDto type: " + dto.getClass());
    }
}
