package com.project.transportation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.Set;

@Entity
public class Client extends BaseEntity {
    private String name;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "client")
    private Set<Transportation> transportations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Set<Transportation> getTransportations() {
        return transportations;
    }

    public void setTransportations(Set<Transportation> transportations) {
        this.transportations = transportations;
    }
}
