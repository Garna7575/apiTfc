package com.tfc.apitfc.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.List;

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
    @JsonBackReference(value = "neighborhood-commonareas")
    private Neighborhood neighborhood;

    @OneToMany(mappedBy = "commonArea")
    @JsonManagedReference(value = "commonarea-neighbors")
    private List<Neighbor> neighbors;

    @OneToMany(mappedBy = "commonArea")
    @JsonManagedReference(value = "commonarea-reservations")
    private List<Reservation> reservations;

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

    public List<Neighbor> getNeighbor() {
        return neighbors;
    }

    public void setNeighbor(List<Neighbor> neighbor) {
        this.neighbors = neighbor;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
