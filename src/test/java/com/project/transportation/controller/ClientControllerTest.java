package com.project.transportation.controller;

import com.project.transportation.dto.ClientDto;
import com.project.transportation.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)  // Enable Mockito extension for JUnit 5
public class ClientControllerTest {

    @Mock
    private ClientService clientService;  // Mock the service

    @InjectMocks
    private ClientController clientController;  // Inject the mock service into the controller

    private ClientDto clientDto;

    @BeforeEach
    void setUp() {
        // Initialize a test ClientDto
        clientDto = new ClientDto(1, "Test Client", "testclient@example.com", "0888888888");
    }

    @Test
    void testGetClientById() {
        // Arrange: Define behavior of the mock service
        when(clientService.getClientById(1)).thenReturn(clientDto);

        // Act: Call the controller method
        ResponseEntity<ClientDto> response = clientController.getClientById(1);

        // Assert: Verify the response
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(clientDto);

        // Verify that the service method was called once
        verify(clientService, times(1)).getClientById(1);
    }

    @Test
    void testAddClient() {
        // Arrange: Define behavior of the mock service
        when(clientService.addClient(any(ClientDto.class))).thenReturn(clientDto);

        // Act: Call the controller method
        ResponseEntity<ClientDto> response = clientController.addClient(clientDto);

        // Assert: Verify the response
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(clientDto);

        // Verify that the service method was called once
        verify(clientService, times(1)).addClient(any(ClientDto.class));
    }

    @Test
    void testUpdateClient() {
        // Arrange: Define behavior of the mock service
        when(clientService.updateClient(any(ClientDto.class))).thenReturn(clientDto);

        // Act: Call the controller method
        ResponseEntity<ClientDto> response = clientController.updateClient(clientDto);

        // Assert: Verify the response
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(clientDto);

        // Verify that the service method was called once
        verify(clientService, times(1)).updateClient(any(ClientDto.class));
    }

    @Test
    void testDeleteClient() {
        // Act: Call the controller method
        ResponseEntity<?> response = clientController.deleteClient(1);

        // Assert: Verify the response
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        // Verify that the service method was called once
        verify(clientService, times(1)).deleteClient(1);
    }

    @Test
    void testGetAllClients() {
        // Arrange: Create a list of clients and define behavior of the mock service
        when(clientService.getAllClients()).thenReturn(List.of(clientDto));

        // Act: Call the controller method
        ResponseEntity<List<ClientDto>> response = clientController.getAllClients();

        // Assert: Verify the response
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsExactly(clientDto);

        // Verify that the service method was called once
        verify(clientService, times(1)).getAllClients();
    }

    @Test
    void testHasDebts() {
        // Arrange: Define behavior of the mock service
        when(clientService.hasClientDebts(anyInt())).thenReturn(true);

        // Act: Call the controller method
        ResponseEntity<Boolean> response = clientController.hasDebts(1);

        // Assert: Verify the response
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isTrue();

        // Verify that the service method was called once
        verify(clientService, times(1)).hasClientDebts(1);
    }
}

