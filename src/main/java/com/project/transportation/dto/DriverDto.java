package com.project.transportation.dto;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class DriverDto extends EmployeeDto {
    private Integer vehicleId;
    private Set<Integer> qualificationIds;

    public DriverDto(Integer id, String firstName, String lastName, String email, Integer companyId, Integer vehicleId, Set<Integer> qualifications, double salary) {
        super(id, firstName, lastName, email, companyId, salary);
        this.vehicleId = vehicleId;
        this.qualificationIds = qualifications != null ? qualifications : new HashSet<>(); // Prevent null qualifications
    }

    public Integer getVehicleId() {
        return vehicleId;
    }

    public Set<Integer> getQualificationIds() {
        return qualificationIds;
    }

    public void setVehicleId(Integer vehicleId) {
        this.vehicleId = vehicleId;
    }

    public void setQualificationIds(Set<Integer> qualificationIds) {
        this.qualificationIds = qualificationIds != null ? qualificationIds : new HashSet<>();
    }

    // Comparator for sorting by qualification points in ascending order
    public static Comparator<DriverDto> byQualificationPointsAscending() {
        return Comparator.comparingInt(DriverDto::getTotalQualificationPoints);
    }

    // Comparator for sorting by qualification points in descending order
    public static Comparator<DriverDto> byQualificationPointsDescending() {
        return Comparator.comparingInt(DriverDto::getTotalQualificationPoints).reversed();
    }

    // Calculate the total qualification points (assuming qualifications are stored as integers)
    public int getTotalQualificationPoints() {
        // Assuming qualificationIds contains integer points for each qualification
        return qualificationIds.stream().mapToInt(Integer::intValue).sum();
    }
}
