package com.tfc.apitfc.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Neighbor extends AppUser {

    @Column(nullable = false)
    private String house;

    @ManyToOne
    @JoinColumn(name = "neighborhood_id")
    private Neighborhood neighborhood;

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public Neighborhood getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(Neighborhood neighborhood) {
        this.neighborhood = neighborhood;
    }
}