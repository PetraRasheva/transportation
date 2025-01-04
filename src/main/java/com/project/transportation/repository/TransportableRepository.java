package com.project.transportation.repository;

import com.project.transportation.model.Transportable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportableRepository extends JpaRepository<Transportable, Integer> {
}
