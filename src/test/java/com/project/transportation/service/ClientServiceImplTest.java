package com.project.transportation.service;

import com.project.transportation.dto.ClientDto;
import com.project.transportation.exception.ClientNotFoundException;
import com.project.transportation.mapper.ClientMapper;
import com.project.transportation.model.Client;
import com.project.transportation.repository.ClientRepository;
import com.project.transportation.repository.TransportationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClientServiceImplTest {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    @Mock
    private TransportationRepository transportationRepository;

    @InjectMocks
    private ClientServiceImpl clientService;

    private ClientDto clientDto;
    private Client client;

    @BeforeEach
    void setUp() {
        client = new Client();
        client.setName("Test Client");
        client.setEmail("test@example.com");
        client.setPhone("123456789");

        clientDto = new ClientDto(1, "Test Client", "test@example.com", "123456789");
    }

    @Test
    void testAddClient() {
        when(clientMapper.toEntity(clientDto)).thenReturn(client);
        when(clientRepository.save(client)).thenReturn(client);
        when(clientMapper.toDto(client)).thenReturn(clientDto);

        ClientDto result = clientService.addClient(clientDto);

        assertThat(result).isEqualTo(clientDto);
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void testGetClientById() {
        when(clientRepository.findById(1)).thenReturn(Optional.of(client));
        when(clientMapper.toDto(client)).thenReturn(clientDto);

        ClientDto result = clientService.getClientById(1);

        assertThat(result).isEqualTo(clientDto);
        verify(clientRepository, times(1)).findById(1);
    }

    @Test
    void testGetClientById_ClientNotFound() {
        when(clientRepository.findById(1)).thenReturn(Optional.empty());

        try {
            clientService.getClientById(1);
        } catch (ClientNotFoundException e) {
            assertThat(e).isInstanceOf(ClientNotFoundException.class)
                    .hasMessage("Client not found");
        }

        verify(clientRepository, times(1)).findById(1);
    }

    @Test
    void testUpdateClient() {
        when(clientRepository.findById(clientDto.id())).thenReturn(Optional.of(client));
        when(clientMapper.toDto(client)).thenReturn(clientDto);

        ClientDto result = clientService.updateClient(clientDto);

        assertThat(result).isEqualTo(clientDto);
        verify(clientRepository, times(1)).findById(clientDto.id());
    }

    @Test
    void testDeleteClient() {
        doNothing().when(clientRepository).deleteById(1);

        clientService.deleteClient(1);

        verify(clientRepository, times(1)).deleteById(1);
    }

    @Test
    void testHasClientDebts() {
        when(transportationRepository.hasUnpaidTransportations(1)).thenReturn(true);

        boolean result = clientService.hasClientDebts(1);

        assertThat(result).isTrue();
        verify(transportationRepository, times(1)).hasUnpaidTransportations(1);
    }

    @Test
    void testGetAllClients() {
        when(clientRepository.findAll()).thenReturn(List.of(client));
        when(clientMapper.toDto(client)).thenReturn(clientDto);

        List<ClientDto> result = clientService.getAllClients();

        assertThat(result).containsExactly(clientDto);
        verify(clientRepository, times(1)).findAll();
    }
}

