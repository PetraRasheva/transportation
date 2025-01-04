package com.project.transportation.service;

import com.project.transportation.dto.ClientDto;

import java.util.List;

public interface ClientService {
    ClientDto addClient(ClientDto clientDto);

    ClientDto getClientById(Integer id);

    ClientDto updateClient(ClientDto client);

   // List<ClientDto> getAllClients();

    void deleteClient(Integer id);

    boolean hasClientDebts(Integer clientId);
}
