package com.project.transportation.repository;

import com.project.transportation.model.Transportation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TransportationRepository extends JpaRepository<Transportation, Integer> {
    Optional<Transportation>  findTransportationById(Integer id);

    List<Transportation> findAllByOrderByEndDestinationAsc();

    List<Transportation> findAllByOrderByEndDestinationDesc();

    List<Transportation> findAllByEndDestination(String endDestination);

    @Query("SELECT COUNT(t) > 0 FROM Transportation t WHERE t.client.id = :clientId AND t.isPaid = false")
    boolean hasUnpaidTransportations(@Param("clientId") Integer clientId);

    @Query("SELECT SUM(t.price) FROM Transportation t")
    Double getTotalPriceOfAllTransportations();

    long count();
}
