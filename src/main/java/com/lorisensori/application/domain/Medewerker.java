package com.lorisensori.application.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.lorisensori.application.domain.audit.DateAudit;
import com.lorisensori.application.validator.NullOrNotBlank;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "MEDEWERKER")
public class Medewerker extends DateAudit {
    @Id
    @Column(name = "MEDEWERKERID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", allocationSize = 1)
    private Long id;

    @NaturalId
    @NotNull(message = "gebruikersnaam kan niet leeg zijn")
    @Column(name = "GEBRUIKERSNAAM", unique = true)
    private String gebruikersnaam;

    @Column(name = "VOORNAAM")
    @NullOrNotBlank(message = "Voornaam kan niet leeg zijn")
    private String voornaam;

    @Column(name = "ACHTERNAAM")
    @NullOrNotBlank(message = "Achternaam kan niet leeg zijn")
    private String achternaam;

    @Column(name = "PASSWORD")
    @NotNull(message = "wachtwoord kan niet leeg zijn")
    private String password;

    @NaturalId
    @Column(name = "EMAIL", unique = true)
    @NotBlank(message = "Gebruikers email kan niet leeg zijn")
    private String email;

    @Column(name = "TELEFOONNUMMER")
    @NullOrNotBlank(message = "telefoonnummer kan niet leeg zijn")
    private String telefoonnummer;

    @ManyToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "bedrijfsnaam")
    @JsonBackReference(value = "medewerkers")
    private Bedrijf bedrijf;

    @ManyToOne(optional = true, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "tank_id")
    private Tank tank;

    @Column(name = "IS_ACTIVE", nullable = false)
    private Boolean active;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "MEDEWERKER_RECHTEN", joinColumns = {
            @JoinColumn(name = "MEDEWERKERID", referencedColumnName = "MEDEWERKERID")}, inverseJoinColumns = {
            @JoinColumn(name = "RECHT_ID", referencedColumnName = "RECHT_ID")})
    private Set<Recht> rechten = new HashSet<>();

    @Column(name = "IS_EMAIL_VERIFIED", nullable = false)
    private Boolean isEmailVerified;


    /////////////////////////////////////////
    //CONSTRUCTORS

    public Medewerker() {
        super();
    }

    public Medewerker(Medewerker medewerker) {
        id = medewerker.getId();
        gebruikersnaam = medewerker.getGebruikersnaam();
        voornaam = medewerker.getVoornaam();
        achternaam = medewerker.getAchternaam();
        password = medewerker.getPassword();
        email = medewerker.getEmail();
        telefoonnummer = medewerker.getTelefoonnummer();
        bedrijf = medewerker.getBedrijf();
        tank = medewerker.getTank();
        active = medewerker.getActive();
        rechten = medewerker.getRechten();
        isEmailVerified = medewerker.getEmailVerified();


    }

///////////////////////////////////////////////////////////////////
    //GETTERS & SETTERS


    @Override
    public String toString() {
        return "Medewerker{" +
                "medewerkerId=" + id +
                ", gebruikersnaam='" + gebruikersnaam + '\'' +
                ", voornaam='" + voornaam + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", wachtwoord='" + password + '\'' +
                ", email='" + email + '\'' +
                ", telefoonnummer='" + telefoonnummer + '\'' +
                '}';
    }

    public void confirmVerificiation() {
        setEmailVerified(true);
    }


    public void addRecht(Recht recht) {
        rechten.add(recht);
        recht.getUserList().add(this);
    }

    public void addRechten(Set<Recht> rechten) {
        rechten.forEach(this::addRecht);
    }

    public void removeRecht(Recht recht) {
        rechten.remove(recht);
        recht.getUserList().remove(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long medewerkerId) {
        this.id = medewerkerId;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefoonnummer() {
        return telefoonnummer;
    }

    public void setTelefoonnummer(String telefoonnummer) {
        this.telefoonnummer = telefoonnummer;
    }

    public Bedrijf getBedrijf() {
        return bedrijf;
    }

    public void setBedrijf(Bedrijf bedrijf) {
        this.bedrijf = bedrijf;
    }

    public Tank getTank() {
        return tank;
    }

    public void setTank(Tank tank) {
        this.tank = tank;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Recht> getRechten() {
        return rechten;
    }

    public void setRechten(Set<Recht> rechten) {
        this.rechten = rechten;
    }

    public Boolean getEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(Boolean isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }
}
