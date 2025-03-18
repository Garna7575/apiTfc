package com.tfc.apitfc.domain.entity;

import jakarta.persistence.*;

@Entity
public class CommonArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "isBooked", nullable = false)
    private boolean isBooked;

    @ManyToOne
    @JoinColumn(name = "neighborhood_id")
    private Neighborhood neighborhood;

    @OneToOne(mappedBy = "commonArea")
    private Neighbor neighbor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void setBooked(boolean booked) {
        isBooked = booked;
    }

    public Neighborhood getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(Neighborhood neighborhood) {
        this.neighborhood = neighborhood;
    }

    public Neighbor getNeighbor() {
        return neighbor;
    }

    public void setNeighbor(Neighbor neighbor) {
        this.neighbor = neighbor;
    }
}
