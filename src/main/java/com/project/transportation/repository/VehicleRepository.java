package com.project.transportation.repository;

import com.project.transportation.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer> {
    Optional<Vehicle> findVehicleById(Integer id);

    void deleteVehicleById(Integer id);
}
