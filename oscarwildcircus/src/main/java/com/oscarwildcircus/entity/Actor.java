package com.oscarwildcircus.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(min = 1, max =255)
    @NotNull
    private String name;
    @Column(length = 2000)
    private String biography;

    private String portraitUrl;
    @Transient
    private MultipartFile portrait;

    @OneToMany(mappedBy = "actor")
    private Set<Activity> activities;

    public Actor(){
        this.activities= new HashSet<>();
    }


    public Set<Activity> getActivities() {
        return activities;
    }

    public void setActivities(Set<Activity> activities) {
        this.activities = activities;
    }

}
