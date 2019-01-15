package com.lorisensori.application.DTOs.tankDTOs;

import java.util.Date;

public class SensorgegevensExtraDTO extends SensorgegevensDTO {
    private Long tankId;
    private String devId;
    private Date openingstijd;
    private Date sluitingstijd;

    public Long getTankId() {
        return tankId;
    }

    public void setTankId(Long tankId) {
        this.tankId = tankId;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
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
