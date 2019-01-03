package com.lorisensori.application.DTOs.tankDTOs;

import com.lorisensori.application.enums.StatusEnums;

import java.util.Date;

/**
 * Created by falco on Dec, 2018
 */
public class UpdateTankDTO {

    private String tanknaam, type;

    private StatusEnums status;

    private Date openingstijd, sluitingstijd;

    public UpdateTankDTO(String tanknaam, String type, StatusEnums status, Date openingstijd, Date sluitingstijd) {
        this.tanknaam = tanknaam;
        this.type = type;
        this.status = status;
        this.openingstijd = openingstijd;
        this.sluitingstijd = sluitingstijd;
    }

    public UpdateTankDTO(){}

    /////////////////////////////////////////////
    //GETTERS AND SETTERS

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
}
