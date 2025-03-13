package com.tfc.apitfc.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Neighbor")
@Getter
@Setter
public class Neighbor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "neighbor_id_gen")
    @SequenceGenerator(name = "neighbor_id_gen", sequenceName = "neighbor_id_gen", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private int id;

    @Size(max = 10)
    @NotNull
    @Column(name = "houseNumber", nullable = false, length = 10)
    private String houseNumber;
}
