package com.tfc.apitfc.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "neighborhood")
@Getter
@Setter
public class Neighborhood {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "neighborhood_id_gen")
    @SequenceGenerator(name = "neighborhood_id_gen", sequenceName = "neighborhood_id_gen", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private int id;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "adminId", updatable = true, nullable = false)
    private Admin admin;

}
