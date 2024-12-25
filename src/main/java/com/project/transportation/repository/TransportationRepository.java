package com.project.transportation.repository;

import com.project.transportation.model.Transportation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransportationRepository extends JpaRepository<Transportation, Integer> {
    Optional<Transportation>  findTransportationById(Integer id);

    void deleteTransportationById(Integer id);
}
