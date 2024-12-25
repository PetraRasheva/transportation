package com.project.transportation.dto;

import java.util.HashSet;
import java.util.Set;

public class DriverDto extends EmployeeDto {
    private Integer vehicleId;
    private Set<Integer> qualificationIds;

    public DriverDto(Integer id, String firstName, String lastName, String email, Integer companyId, Integer vehicleId, Set<Integer> qualifications) {
        super(id, firstName, lastName, email, companyId);
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
}
