package com.lorisensori.application.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lorisensori.application.enums.StatusEnums;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tank")
public class Tank implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tankId;

    @NotBlank
    @Column
    private String tanknaam;

    private int tanknummer;

    private String type;

    @Column(unique = true)
    private String devId;//TTN device ID

    private int inhoudLiters;

    private int bouwjaar;

    private int diameter;
    private int lengte;
    private double gewicht;

    @Enumerated(EnumType.STRING)
    private StatusEnums status;

    private Date openingstijd;
    private Date sluitingstijd;

    private int meldingTanken;

    @ManyToOne
    @JoinColumn(name = "bedrijfsnaam")
    @JsonBackReference(value = "tanks")
    private Bedrijf bedrijf;

    @OneToMany(mappedBy = "tank", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Sensorgegevens> sensorgegevens;

    @OneToMany(mappedBy = "tank", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<SensorLog> sensorLog;


    //TODO: LoRa informatie toevoegen

    public Tank() {

    }

    public void addSensorLog(SensorLog sensorLog){ //gebruiken we om de sensorlog bij te houden
        getSensorLog().add(sensorLog);
    }

    public void addSensorGegevens(Sensorgegevens sensorgegevens){
        getSensorgegevens().add(sensorgegevens);
    }
///////////////////////////////////////////////////////////////////
    //GETTERS & SETTERS


    public Long getTankId() {
        return tankId;
    }

    public void setTankId(Long tankId) {
        this.tankId = tankId;
    }

    public String getTanknaam() {
        return tanknaam;
    }

    public void setTanknaam(String tanknaam) {
        this.tanknaam = tanknaam;
    }

    public int getTanknummer() {
        return tanknummer;
    }

    public void setTanknummer(int tanknummer) {
        this.tanknummer = tanknummer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }

    public int getInhoudLiters() {
        return inhoudLiters;
    }

    public void setInhoudLiters(int inhoudLiters) {
        this.inhoudLiters = inhoudLiters;
    }

    public int getBouwjaar() {
        return bouwjaar;
    }

    public void setBouwjaar(int bouwjaar) {
        this.bouwjaar = bouwjaar;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public int getLengte() {
        return lengte;
    }

    public void setLengte(int lengte) {
        this.lengte = lengte;
    }

    public double getGewicht() {
        return gewicht;
    }

    public void setGewicht(double gewicht) {
        this.gewicht = gewicht;
    }

    public StatusEnums getStatus() {
        return status;
    }

    public void setStatus(StatusEnums status) {
        this.status = status;
    }

    public Date getOpeningstijd() {
        return openingstijd;
    }

    public void setOpeningstijd(Date openingstijd) {
        this.openingstijd = openingstijd;
    }

    public Date getSluitingstijd() {
        return sluitingstijd;
    }

    public void setSluitingstijd(Date sluitingstijd) {
        this.sluitingstijd = sluitingstijd;
    }

    public int getMeldingTanken() {
        return meldingTanken;
    }

    public void setMeldingTanken(int meldingTanken) {
        this.meldingTanken = meldingTanken;
    }

    public Bedrijf getBedrijf() {
        return bedrijf;
    }

    public void setBedrijf(Bedrijf bedrijf) {
        this.bedrijf = bedrijf;
    }

    public Set<Sensorgegevens> getSensorgegevens() {
        return sensorgegevens;
    }

    public void setSensorgegevens(Set<Sensorgegevens> sensorgegevens) {
        this.sensorgegevens = sensorgegevens;
    }

    public Set<SensorLog> getSensorLog() {
        return sensorLog;
    }

    public void setSensorLog(Set<SensorLog> sensorLog) {
        this.sensorLog = sensorLog;
    }
}

