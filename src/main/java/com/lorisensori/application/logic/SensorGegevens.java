package com.lorisensori.application.logic;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sensorGegevens")
public class SensorGegevens {

    @Column(nullable = false)
    boolean slotStatus;

    @Column(nullable = false)
    private double dieselniveau, accuniveau, vermogenZonnepaneel;

    @Column(nullable = false)
    int gpsBreedtegraag, gpsLengtegraag;

    @Id
    @CreatedDate
    private Date createdAt;
}
