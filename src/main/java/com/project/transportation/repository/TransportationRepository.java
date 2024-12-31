package com.project.transportation.repository;

import com.project.transportation.model.Transportation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransportationRepository extends JpaRepository<Transportation, Integer> {
    Optional<Transportation>  findTransportationById(Integer id);

    List<Transportation> findAllByOrderByEndDestinationAsc();
    List<Transportation> findAllByOrderByEndDestinationDesc();
    List<Transportation> findAllByEndDestination(String endDestination);
}
