package com.tfc.apitfc.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@PrimaryKeyJoinColumn(name = "id")
public class Neighbor extends AppUser {

    @Column(nullable = false)
    private String house;

    @ManyToOne
    @JoinColumn(name = "neighborhood_id")
    @JsonBackReference
    private Neighborhood neighborhood;

    @ManyToOne()
    @JoinColumn(name = "reservation", nullable = true, foreignKey = @ForeignKey(value = ConstraintMode.CONSTRAINT, name = "fk_neighbor_commonarea"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference(value = "commonarea-neighbors")
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

    public CommonArea getCommonArea() {
        return commonArea;
    }

    public void setCommonArea(CommonArea commonArea) {
        this.commonArea = commonArea;
    }
}