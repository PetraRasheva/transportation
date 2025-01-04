package com.project.transportation.controller;

import com.project.transportation.dto.ClientDto;
import com.project.transportation.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable("id") Integer id) {
        ClientDto client = clientService.getClientById(id);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ClientDto> addClient(@RequestBody ClientDto client) {
        ClientDto newClient = clientService.addClient(client);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ClientDto> updateClient(@RequestBody ClientDto client) {
        ClientDto updateClient = clientService.updateClient(client);
        return new ResponseEntity<>(updateClient, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable("id") Integer id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/hasDebts")
    public ResponseEntity<Boolean> hasDebts(@PathVariable("id") Integer clientId) {
        boolean hasDebts = clientService.hasClientDebts(clientId);
        return new ResponseEntity<>(hasDebts, HttpStatus.OK);
    }
}
