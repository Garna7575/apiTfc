package com.tfc.apitfc.domain.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "neighborhood")
public class Neighborhood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "neighborhood")
    private List<Neighbor> neighbors;

    @OneToMany(mappedBy = "id")
    private List<CommonArea> commonAreas;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Neighbor> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Neighbor> neighbors) {
        this.neighbors = neighbors;
    }
}
