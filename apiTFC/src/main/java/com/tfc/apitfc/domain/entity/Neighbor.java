package com.tfc.apitfc.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Neighbor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @JsonBackReference(value = "neighbor-user")
    private AppUser user;

    @Column(nullable = false)
    private String house;

    @ManyToOne
    @JoinColumn(name = "neighborhood_id")
    @JsonBackReference(value = "neighborhood-neighbors")
    private Neighborhood neighborhood;

    @ManyToOne
    @JoinColumn(name = "reservation_id", nullable = true)
    @JsonBackReference(value = "commonarea-neighbors")
    private CommonArea commonArea;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

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

    public CommonArea getCommonArea() {
        return commonArea;
    }

    public void setCommonArea(CommonArea commonArea) {
        this.commonArea = commonArea;
    }
}
