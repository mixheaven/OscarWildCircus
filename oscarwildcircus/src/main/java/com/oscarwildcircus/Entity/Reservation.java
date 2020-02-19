package com.oscarwildcircus.Entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private int placeMax;

    private int placeWanted;

    private Date date;

    @OneToOne
    private Person person;

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
