package com.project.transportation.service;

import com.project.transportation.dto.ClientDto;
import com.project.transportation.exception.ClientNotFoundException;
import com.project.transportation.mapper.ClientMapper;
import com.project.transportation.model.Client;
import com.project.transportation.repository.ClientRepository;
import com.project.transportation.repository.TransportationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {
    public final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final TransportationRepository transportationRepository;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper, TransportationRepository transportationRepository) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.transportationRepository = transportationRepository;
    }

    @Override
    public ClientDto addClient(ClientDto clientDto) {
        Client client = clientRepository.save(clientMapper.toEntity(clientDto));
        return clientMapper.toDto(client);
    }
    
    @Override
    public ClientDto getClientById(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
        return clientMapper.toDto(client);
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto) {
        Client client = clientRepository.findById(clientDto.id())
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));

        clientMapper.updateClientFromDto(clientDto, client);
        return clientMapper.toDto(client);
    }

    @Override
    public void deleteClient(Integer id) {
        clientRepository.deleteById(id);
    }

    @Override
    public boolean hasClientDebts(Integer clientId) {
        return transportationRepository.hasUnpaidTransportations(clientId);
    }

    @Override
    public List<ClientDto> getAllClients() {
        return clientRepository.findAll().stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }
}
