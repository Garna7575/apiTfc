package com.tfc.apitfc.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id", nullable = false)
    private Users users;

    @Size(max = 20)
    @NotNull
    @Column(name = "registration_number", nullable = false, length = 20)
    private String registrationNumber;  

    @OneToMany(mappedBy = "admin")
    private List<Neighborhood> neighborhoods;
}