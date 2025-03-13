package com.tfc.apitfc.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Admin")
@PrimaryKeyJoinColumn(name = "id")
@Getter
@Setter
public class Admin extends Users {

    @NotNull
    @Column(name = "registrationNumber", nullable = false, length = 20)
    private String registrationNumber;
}
