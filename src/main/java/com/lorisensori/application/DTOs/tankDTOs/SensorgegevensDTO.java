package com.lorisensori.application.DTOs.tankDTOs;

import java.io.Serializable;
import java.util.Date;

import com.lorisensori.application.domain.Medewerker;

public class SensorgegevensDTO implements Serializable {
    private Long sensorId;

    private int uplinkId;
    private int slotStatus;
    private int dieselniveau;
    private int accuniveau;
    private int vermogenZonnepaneel;
    private int gpsBreedtegraad;
    private int gpsBreedteMinuut;
    private int gpsBreedteSeconde;
    private int gpsBreedteTiendeSec;
    private int gpsLengtegraad;
    private int gpsLengteMinuut;
    private int gpsLengteSeconde;
    private int gpsLengteTiendeSec;
    private Date timestamp;

    public SensorgegevensDTO() {

    }

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }

    public int isSlotStatus() {
        return slotStatus;
    }

    public void setSlotStatus(int slotStatus) {
        this.slotStatus = slotStatus;
    }

    public int getDieselniveau() {
        return dieselniveau;
    }

    public void setDieselniveau(int dieselniveau) {
        this.dieselniveau = dieselniveau;
    }

    public int getAccuniveau() {
        return accuniveau;
    }

    public void setAccuniveau(int accuniveau) {
        this.accuniveau = accuniveau;
    }

    public int getVermogenZonnepaneel() {
        return vermogenZonnepaneel;
    }

    public void setVermogenZonnepaneel(int vermogenZonnepaneel) {
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

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /*
        public Medewerker getMedewerkerSlot() {
            return medewerkerSlot;
        }

        public void setMedewerkerSlot(Medewerker medewerkerSlot) {
            this.medewerkerSlot = medewerkerSlot;
        }
    */
    public int getUplinkId() {
        return uplinkId;
    }

    public void setUplinkId(int uplinkId) {
        this.uplinkId = uplinkId;
    }

    public int getGpsBreedteMinuut() {
        return gpsBreedteMinuut;
    }

    public void setGpsBreedteMinuut(int gpsBreedteMinuut) {
        this.gpsBreedteMinuut = gpsBreedteMinuut;
    }

    public int getGpsBreedteSeconde() {
        return gpsBreedteSeconde;
    }

    public void setGpsBreedteSeconde(int gpsBreedteSeconde) {
        this.gpsBreedteSeconde = gpsBreedteSeconde;
    }

    public int getGpsBreedteTiendeSec() {
        return gpsBreedteTiendeSec;
    }

    public void setGpsBreedteTiendeSec(int gpsBreedteTiendeSec) {
        this.gpsBreedteTiendeSec = gpsBreedteTiendeSec;
    }

    public int getGpsLengteMinuut() {
        return gpsLengteMinuut;
    }

    public void setGpsLengteMinuut(int gpsLengteMinuut) {
        this.gpsLengteMinuut = gpsLengteMinuut;
    }

    public int getGpsLengteSeconde() {
        return gpsLengteSeconde;
    }

    public void setGpsLengteSeconde(int gpsLengteSeconde) {
        this.gpsLengteSeconde = gpsLengteSeconde;
    }

    public int getGpsLengteTiendeSec() {
        return gpsLengteTiendeSec;
    }

    public void setGpsLengteTiendeSec(int gpsLengteTiendeSec) {
        this.gpsLengteTiendeSec = gpsLengteTiendeSec;
    }


}
