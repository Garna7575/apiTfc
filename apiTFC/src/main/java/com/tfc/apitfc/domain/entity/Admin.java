package com.tfc.apitfc.domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Admin extends AppUser {

    @Column(nullable = false, unique = true)
    private String registrationNumber;

    @OneToMany(mappedBy = "id")
    List<Neighborhood> neighborhoods;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public List<Neighborhood> getNeighborhoods() {
        return neighborhoods;
    }

    public void setNeighborhoods(List<Neighborhood> neighborhoods) {
        this.neighborhoods = neighborhoods;
    }
}
