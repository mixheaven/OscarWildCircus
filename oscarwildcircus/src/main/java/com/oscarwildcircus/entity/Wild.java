package com.oscarwildcircus.entity;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Data
@Entity
public class Wild {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(length = 5000)
    private String description;
    private String about;
    private String pictureUrl;
    @Transient
    private MultipartFile picture;
}
