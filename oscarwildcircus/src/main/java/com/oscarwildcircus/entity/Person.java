package com.oscarwildcircus.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(min = 1, max =255)
    @NotNull
    private String firstName;
    @Size(min = 1, max =255)
    @NotNull
    private String lastName;
    @Size(min = 1, max =255)
    @NotNull
    private String address;
    @Size(min = 1, max =255)
    @NotNull
    private String mail;
    @Size(min = 1, max =255)
    @NotNull
    private String phone;

    @OneToOne
    private Reservation reservation;

}
