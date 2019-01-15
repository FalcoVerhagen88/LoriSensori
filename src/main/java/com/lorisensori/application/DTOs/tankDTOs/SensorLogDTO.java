package com.lorisensori.application.DTOs.tankDTOs;

import java.util.Date;

public class SensorLogDTO {
    private Date timestamp;
    private String sensorLogId;
    private String uplinkId;
    private String slotStatus;
    private String dieselniveau;
    private String accuniveau;
    private String vermogenZonnepaneel;
    private String gpsBreedtegraad;
    private String gpsBreedteMinuut;
    private String gpsBreedteSeconde;
    private String gpsBreedteTiendeSec;
    private String gpsLengtegraad;
    private String gpsLengteMinuut;
    private String gpsLengteSeconde;
    private String gpsLengteTiendeSec;
    private String weekendSetting;

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getSensorLogId() {
        return sensorLogId;
    }

    public void setSensorLogId(String sensorLogId) {
        this.sensorLogId = sensorLogId;
    }

    public String getUplinkId() {
        return uplinkId;
    }

    public void setUplinkId(String uplinkId) {
        this.uplinkId = uplinkId;
    }

    public String getSlotStatus() {
        return slotStatus;
    }

    public void setSlotStatus(String slotStatus) {
        this.slotStatus = slotStatus;
    }

    public String getDieselniveau() {
        return dieselniveau;
    }

    public void setDieselniveau(String dieselniveau) {
        this.dieselniveau = dieselniveau;
    }

    public String getAccuniveau() {
        return accuniveau;
    }

    public void setAccuniveau(String accuniveau) {
        this.accuniveau = accuniveau;
    }

    public String getVermogenZonnepaneel() {
        return vermogenZonnepaneel;
    }

    public void setVermogenZonnepaneel(String vermogenZonnepaneel) {
        this.vermogenZonnepaneel = vermogenZonnepaneel;
    }

    public String getGpsBreedtegraad() {
        return gpsBreedtegraad;
    }

    public void setGpsBreedtegraad(String gpsBreedtegraad) {
        this.gpsBreedtegraad = gpsBreedtegraad;
    }

    public String getGpsBreedteMinuut() {
        return gpsBreedteMinuut;
    }

    public void setGpsBreedteMinuut(String gpsBreedteMinuut) {
        this.gpsBreedteMinuut = gpsBreedteMinuut;
    }

    public String getGpsBreedteSeconde() {
        return gpsBreedteSeconde;
    }

    public void setGpsBreedteSeconde(String gpsBreedteSeconde) {
        this.gpsBreedteSeconde = gpsBreedteSeconde;
    }

    public String getGpsBreedteTiendeSec() {
        return gpsBreedteTiendeSec;
    }

    public void setGpsBreedteTiendeSec(String gpsBreedteTiendeSec) {
        this.gpsBreedteTiendeSec = gpsBreedteTiendeSec;
    }

    public String getGpsLengtegraad() {
        return gpsLengtegraad;
    }

    public void setGpsLengtegraad(String gpsLengtegraad) {
        this.gpsLengtegraad = gpsLengtegraad;
    }

    public String getGpsLengteMinuut() {
        return gpsLengteMinuut;
    }

    public void setGpsLengteMinuut(String gpsLengteMinuut) {
        this.gpsLengteMinuut = gpsLengteMinuut;
    }

    public String getGpsLengteSeconde() {
        return gpsLengteSeconde;
    }

    public void setGpsLengteSeconde(String gpsLengteSeconde) {
        this.gpsLengteSeconde = gpsLengteSeconde;
    }

    public String getGpsLengteTiendeSec() {
        return gpsLengteTiendeSec;
    }

    public void setGpsLengteTiendeSec(String gpsLengteTiendeSec) {
        this.gpsLengteTiendeSec = gpsLengteTiendeSec;
    }

    public String getWeekendSetting() {
        return weekendSetting;
    }

    public void setWeekendSetting(String weekendSetting) {
        this.weekendSetting = weekendSetting;
    }


}
