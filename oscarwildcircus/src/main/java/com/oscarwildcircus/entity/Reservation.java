package com.oscarwildcircus.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Reservation {
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

    private int placeMax;

    private int placeWanted;




    @OneToMany(mappedBy = "reservation")
    private Set<Activity> activities;

    public Reservation(){
        this.activities= new HashSet<>();
    }

    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }
}
