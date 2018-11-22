package com.lorisensori.application.DTO;

import com.lorisensori.application.enums.StatusEnums;
import com.lorisensori.application.logic.Adres;
import com.lorisensori.application.logic.Medewerker;
import com.lorisensori.application.logic.Tank;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "bedrijf")
public class BedrijfDTO implements Serializable {

    private static final long serialVersionUID = 3L;

    @Id
    @Column(name = "bedrijfsnaam")
    private String bedrijfsnaam;

    @NotBlank
    private String telefoonnummer, rekeningnummer, btwNummer, vatNummer, kvkNummer;

    @OneToOne(optional = true)
    @JoinColumn(name = "adrescode")
    private Adres adres;

    @OneToOne(optional = true)
    @JoinColumn(name = "contactpersoon")
    private MedewerkerDTO contactpersoon;

    @Enumerated(EnumType.STRING)
    private StatusEnums status;

    @ManyToMany
    @JoinTable(
            name = "medewerkers_bedrijf",
            joinColumns = @JoinColumn(name = "BEDRIJF_BEDRIJFSNAAM", referencedColumnName = "bedrijfsnaam"),
            inverseJoinColumns = @JoinColumn(name = "MEDEWERKER_GEBRUIKERSNAAM", referencedColumnName = "gebruikersnaam"))
    private List<MedewerkerDTO> medewerkers;

    @OneToMany
    @JoinColumn(name = "tankid", referencedColumnName = "bedrijfsnaam")
    private List<TankDTO> tanks;

    public List<MedewerkerDTO> getMedewerkers() {
        return medewerkers;
    }

    public void setMedewerkers(List<MedewerkerDTO> medewerkers) {
        this.medewerkers = medewerkers;
    }

    public void addMedewerker(MedewerkerDTO medewerker) {
        medewerkers.add(medewerker);
    }

    ///////////////////////////////////////////////////////////////
    //GETTERS AND SETTERS

    public String getBedrijfsnaam() {
        return bedrijfsnaam;
    }

    public void setBedrijfsnaam(String bedrijfsnaam) {
        this.bedrijfsnaam = bedrijfsnaam;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

    public MedewerkerDTO getContactpersoon() {
        return contactpersoon;
    }

    public void setContactpersoon(MedewerkerDTO contactpersoon) {
        this.contactpersoon = contactpersoon;
    }

    public String getRekeningnummer() {
        return rekeningnummer;
    }

    public void setRekeningnummer(String rekeningnummer) {
        this.rekeningnummer = rekeningnummer;
    }

    public String getBtwNummer() {
        return btwNummer;
    }

    public void setBtwNummer(String btwNummer) {
        this.btwNummer = btwNummer;
    }

    public String getVatNummer() {
        return vatNummer;
    }

    public void setVatNummer(String vatNummer) {
        this.vatNummer = vatNummer;
    }

    public String getKvkNummer() {
        return kvkNummer;
    }

    public void setKvkNummer(String kvkNummer) {
        this.kvkNummer = kvkNummer;
    }

    public List<TankDTO> getTanks() {
        return tanks;
    }

    public void setTanks(List<TankDTO> tanks) {
        this.tanks = tanks;
    }

    public void addTank(TankDTO tank) {
        tanks.add(tank);
    }

    public StatusEnums getStatus() {
        return status;
    }

    public void setStatus(StatusEnums status) {
        this.status = status;
    }

    public String toString() {
        return bedrijfsnaam + "(" + adres + ")";
    }

    public boolean loginverificatie(String username, String password) {
        // TODO Auto-generated method stub
        if (username == "Tester" && password == "!Test00") {
            return true;
        }
        return false;
    }
}

