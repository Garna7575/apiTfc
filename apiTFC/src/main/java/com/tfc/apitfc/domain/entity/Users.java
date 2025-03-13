package com.tfc.apitfc.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "Users")
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 15)
    @NotNull
    @Column(name = "username", nullable = false, length = 15)
    private String username;

    @Size(max = 16)
    @NotNull
    @Column(name = "password", nullable = false, length = 16)
    private String password;

    @Size(max = 15)
    @NotNull
    @Column(name = "name", nullable = false, length = 15)
    private String name;

    @Size(max = 20)
    @NotNull
    @Column(name = "surname1", nullable = false, length = 20)
    private String surname1;

    @Size(max = 20)
    @Column(name = "surname2", length = 20)
    private String surname2;

    @NotNull
    @Column(name = "age", nullable = false)
    private int age;
}
