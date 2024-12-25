package com.project.transportation.repository;

import com.project.transportation.model.Driver;
import com.project.transportation.model.Qualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface QualificationRepository extends JpaRepository<Qualification, Integer> {
    @Query("SELECT q FROM Qualification q WHERE :driver MEMBER OF q.drivers")
    Set<Qualification> findAllByDriverId(@Param("driver") Driver driver);
}
