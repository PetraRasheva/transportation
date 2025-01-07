package com.project.transportation.dto;

import com.project.transportation.cache.QualificationCache;

import java.util.HashSet;
import java.util.Set;

public class DriverDto extends EmployeeDto {
    private final Integer vehicleId;
    private Set<Integer> qualificationIds;
    private int totalQualificationPoints;

    public DriverDto(Integer id, String name, String email, Integer companyId, Integer vehicleId, Set<Integer> qualifications, double salary) {
        super(id, name, email, companyId, salary);
        this.vehicleId = vehicleId;
        this.qualificationIds = qualifications != null ? qualifications : new HashSet<>();
        this.totalQualificationPoints = calculateTotalQualificationPoints();  // Initialize total points
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public Set<Integer> getQualificationIds() {
        return qualificationIds;
    }

    public void setTotalQualificationPoints(int totalQualificationPoints) {
        this.totalQualificationPoints = totalQualificationPoints;
    }

    public void setQualificationIds(Set<Integer> qualificationIds) {
        this.qualificationIds = qualificationIds != null ? qualificationIds : new HashSet<>();
        this.totalQualificationPoints = calculateTotalQualificationPoints();  // Recalculate total points when qualifications change
    }

    public int getTotalQualificationPoints() {
        return totalQualificationPoints;
    }

    // Calculate the total qualification points
    private int calculateTotalQualificationPoints() {
        return qualificationIds.stream().mapToInt(QualificationCache::getQualificationPoints).sum();
    }
}