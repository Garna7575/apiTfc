package com.tfc.apitfc.domain.entity;

import jakarta.persistence.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Neighbor extends AppUser {

    @Column(nullable = false)
    private String house;

    @ManyToOne
    @JoinColumn(name = "neighborhood_id")
    private Neighborhood neighborhood;

    @OneToOne
    @JoinColumn(name = "neighbor", nullable = true)
    private CommonArea commonArea;

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