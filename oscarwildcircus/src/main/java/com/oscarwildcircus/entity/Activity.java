package com.oscarwildcircus.entity;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Activity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Size(min = 1, max =255)
    @NotNull
    private String title;
    @Size(min = 1, max =255)
    @NotNull
    private String genre;

    @Size(min = 1, max =255)
    @NotNull
    private String cost;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;

    @DateTimeFormat(pattern = "HH:mm")
    private Date startHour;

    @DateTimeFormat(pattern = "HH:mm")
    private Date endHour;

    @ManyToOne
    private Actor actor;

    @ManyToOne
    private Reservation reservation;
}
