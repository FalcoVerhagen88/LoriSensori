package com.lorisensori.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "sensorgegevens")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"timestamp"}, allowGetters = true)
public class Sensorgegevens implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 10L;

    @Id
    @Column(name = "sensor_id")
    private Long sensorId;

    private boolean slotStatus;
    private double dieselniveau;
    private double accuniveau;
    private double vermogenZonnepaneel;
    private int gpsBreedtegraad;
    private int gpsLengtegraad;
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date timestamp;

    @OneToOne()
    @JoinColumn(name = "slot_geopend_door")
    private Medewerker medewerkerSlot;

    public Sensorgegevens() {
    }

///////////////////////////////////////////////////////////////////
    //GETTERS & SETTERS

    public Long getSensorId() {
        return sensorId;
    }


    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }


    public boolean isSlotStatus() {
        return slotStatus;
    }


    public void setSlotStatus(boolean slotStatus) {
        this.slotStatus = slotStatus;
    }


    public double getDieselniveau() {
        return dieselniveau;
    }


    public void setDieselniveau(double dieselniveau) {
        this.dieselniveau = dieselniveau;
    }


    public double getAccuniveau() {
        return accuniveau;
    }


    public void setAccuniveau(double accuniveau) {
        this.accuniveau = accuniveau;
    }


    public double getVermogenZonnepaneel() {
        return vermogenZonnepaneel;
    }


    public void setVermogenZonnepaneel(double vermogenZonnepaneel) {
        this.vermogenZonnepaneel = vermogenZonnepaneel;
    }


    public int getGpsBreedtegraad() {
        return gpsBreedtegraad;
    }


    public void setGpsBreedtegraad(int gpsBreedtegraad) {
        this.gpsBreedtegraad = gpsBreedtegraad;
    }


    public int getGpsLengtegraad() {
        return gpsLengtegraad;
    }


    public void setGpsLengtegraad(int gpsLengtegraad) {
        this.gpsLengtegraad = gpsLengtegraad;
    }


    public Date getTimestamp() {
        return timestamp;
    }

}
