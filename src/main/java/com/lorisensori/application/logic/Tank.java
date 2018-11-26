package com.lorisensori.application.logic;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lorisensori.application.enums.StatusEnums;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tank")
public class Tank implements Serializable {

    private static final long serialVersionUID = 7608348957147640413L;
    @Id
    @Column(name = "tankId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tankId;

    @Column(nullable = false)
    private String tanknaam, type;

    @Column(nullable = false)
    private int inhoudLiters, bouwjaar, diameter, lengte, meldingTanken;

    @Column(nullable = false)
    private double gewicht;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusEnums status;

    @Column(nullable = false)
    private Date openingstijd, sluitingstijd;

    @ManyToOne
    @JoinColumn(name = "bedrijfId")
    private Bedrijf bedrijf;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ElementCollection
    private List<Medewerker> tankBeheerders;


    public Tank(Long tankId, int diameter, int lengte, int inhoudLiters, int bouwjaar, String tanknaam,
                   String type, double gewicht, StatusEnums status, Date openingstijd, Date sluitingstijd, int meldingTanken) {
        this.tankId = tankId;
        this.diameter = diameter;
        this.lengte = lengte;
        this.inhoudLiters = inhoudLiters;
        this.bouwjaar = bouwjaar;
        this.tanknaam = tanknaam;
        this.type = type;
        this.gewicht = gewicht;
        this.status = status;
        this.openingstijd = openingstijd;
        this.sluitingstijd = sluitingstijd;
        this.meldingTanken = meldingTanken;
    }

    public Tank() {
    }

    public void addTankBeheerder(Medewerker medewerker){
        tankBeheerders.add(medewerker);
    }

    /////////////////////////////////////////////////////
    //GETTERS AND SETTERS


    public List<Medewerker> getTankBeheerders() {
        return tankBeheerders;
    }

    public void setTankBeheerders(List<Medewerker> tankBeheerders) {
        this.tankBeheerders = tankBeheerders;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}

