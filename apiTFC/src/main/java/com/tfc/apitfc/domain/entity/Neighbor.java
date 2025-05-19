package com.tfc.apitfc.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

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

    @OneToMany(mappedBy = "neighbor")
    @JsonManagedReference(value = "neighbor-incidences")
    private List<Incidence> incidences;

    @OneToMany(mappedBy = "neighbor")
    @JsonManagedReference(value = "neighbor-reservations")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "neighbor")
    @JsonManagedReference(value = "neighbor-receipts")
    private List<Receipt> receipts;

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

    public List<Incidence> getIncidences() {
        return incidences;
    }

    public void setIncidences(List<Incidence> incidences) {
        this.incidences = incidences;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}
