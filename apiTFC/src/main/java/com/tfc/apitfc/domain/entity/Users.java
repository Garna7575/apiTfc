package com.tfc.apitfc.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 16)
    @NotNull
    @Column(name = "password", nullable = false, length = 16)
    private String password;

    @Size(max = 15)
    @NotNull
    @Column(name = "name", nullable = false, length = 15)
    private String name;

    @NotNull
    @Column(name = "age", nullable = false)
    private Integer age;

    @Size(max = 20)
    @NotNull
    @Column(name = "surname1", nullable = false, length = 20)
    private String surname1;

    @Size(max = 20)
    @Column(name = "surname2", length = 20)
    private String surname2;

    @Size(max = 15)
    @NotNull
    @Column(name = "username", nullable = false, length = 15)
    private String username;

    @OneToOne(mappedBy = "id")
    private Admin admin;

}