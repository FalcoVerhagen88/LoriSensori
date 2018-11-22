package com.lorisensori.application.DTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "medewerker")
@EntityListeners(AuditingEntityListener.class)//This ensures that the values createdAt and updatedAt get populated automatically
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)//This annotation is used because we don’t want the clients of the rest api to supply the createdAt and updatedAt values. If they supply these values then we’ll simply ignore them. However, we’ll include these values in the JSON response.
public class MedewerkerDTO implements Serializable
{
    private static final long serialVersionUID = 2L;
    @Id
    @Column(name = "gebruikersnaam")
    private String gebruikersnaam;

    @Column
    @NotBlank//this annotation is used to validate that the annotated field is not null or empty.
    private String voornaam, achternaam, wachtwoord, email, telefoonnummer;

    @Column(nullable = false, updatable = false)//this annotation is used to define the properties of the column that will be mapped to the annotated field
    @Temporal(TemporalType.TIMESTAMP)//converts the date and time values from Java Object to compatible database type and vice versa.
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)//this annotation is used to define the properties of the column that will be mapped to the annotated field
    @Temporal(TemporalType.TIMESTAMP)//converts the date and time values from Java Object to compatible database type and vice versa.
    @LastModifiedDate
    private Date updatedAt;


    ///////////////////////////////////////////////////////////////////////////
    //GETTERS AND SETTERS

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

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getWachtwoord() {
        return wachtwoord;
    }

    public void setWachtwoord(String wachtwoord) {
        this.wachtwoord = wachtwoord;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String toString() {
        return "Voornaam: " + voornaam + ", Achternaam: " + achternaam + ", Gebruikersnaam: " + gebruikersnaam + ", Wachtwoord: " + wachtwoord + ", E-mail: " + email +
                ", Telefoonnummer: " + telefoonnummer + ", Gecreëerd op: " + createdAt + "Laatste update: " + updatedAt;

    }
}
