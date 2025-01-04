package com.project.transportation.repository;

import com.project.transportation.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Optional<Client> findClientById(Integer id);

    void deleteClientById(Integer id);
}
