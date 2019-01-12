package com.lorisensori.application.domain;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sensor_log")
//@EntityListeners(AuditingEntityListener.class)
//@JsonIgnoreProperties(value = {"timestamp"}, allowGetters = true)

public class SensorLog {
    /**
     * Overleg met de rest wat to do aanpassen naar int oof uplink aanpassen nar opzet db?
     */
    private static final long serialVersionUID = 10L;

    @Id
    @Column(name = "sensor_log_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sensorLogId; // zo dus


    @Column(updatable = false)
    private String uplinkId; // int gemaakt
    private String slotStatus; // s;otstatus open = 1 dicht = 0
    private String dieselniveau; // niveau van 0 - 100 %
    private String accuniveau; // niveau van 0 tot 100%
    private String vermogenZonnepaneel; // geleverde vermogen in w
    private String gpsBreedtegraad;
    private String gpsBreedteMinuut;
    private String gpsBreedteSeconde;
    private String gpsBreedteTiendeSec;
    private String gpsLengtegraad;
    private String gpsLengteMinuut;
    private String gpsLengteSeconde;
    private String gpsLengteTiendeSec;
    private String weekendSetting;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date timestamp;


    @ManyToOne()
    @JoinColumn(name = "dev_id")
    private Tank tank;


    //@OneToOne()
    //@JoinColumn(name = "slot_geopend_door")
    //private Medewerker medewerkerSlot;


    public SensorLog(String uplinkId, String slotStatus, String dieselniveau, String accuniveau, String vermogenZonnepaneel, String gpsBreedtegraad, String gpsBreedteMinuut,
                     String gpsBreedteSeconde, String gpsBreedteTiendeSec, String gpsLengtegraad, String gpsLengteMinuut, String gpsLengteSeconde, String gpsLengteTiendeSec, String weekendSetting) {

        this.uplinkId = uplinkId;
        this.slotStatus = slotStatus;
        this.dieselniveau = dieselniveau;
        this.accuniveau = accuniveau;
        this.vermogenZonnepaneel = vermogenZonnepaneel;
        this.gpsBreedtegraad = gpsBreedtegraad;
        this.gpsBreedteMinuut = gpsBreedteMinuut;
        this.gpsBreedteSeconde = gpsBreedteSeconde;
        this.gpsBreedteTiendeSec = gpsBreedteTiendeSec;
        this.gpsLengtegraad = gpsLengtegraad;
        this.gpsLengteMinuut = gpsLengteMinuut;
        this.gpsLengteSeconde = gpsLengteSeconde;
        this.gpsLengteTiendeSec = gpsLengteTiendeSec;
        this.weekendSetting = weekendSetting;

    }


    // empty constructor for spring boot
    public SensorLog() {
    }

    ;

    ///////////////////////////////////////////////////////////////////
    //GETTERS & SETTERS

    public Long getSensorLogId() {
        return sensorLogId;
    }

    public void setSensorLogId(Long sensorLogId) {
        this.sensorLogId = sensorLogId;
    }


    public String getSlotStatus() {

        return slotStatus;
    }

    public void setSlotstatus(String slotstatus) {
        this.slotStatus = slotstatus;
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


    public void setVermogenZonnepanel(String vermogen) {
        this.vermogenZonnepaneel = vermogen;
    }


    public String getGpsBreedtegraad() {
        return gpsBreedtegraad;
    }


    public void setGpsBreedtegraad(String breedtegraad) {
        this.gpsBreedtegraad = breedtegraad;
    }


    public String getGpsLengtegraad() {
        return gpsLengtegraad;
    }


    public void setGpsLengtegraad(String lengtegraad) {
        this.gpsLengtegraad = lengtegraad;
    }

    public Date getTimestamp() {
        return timestamp;
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

