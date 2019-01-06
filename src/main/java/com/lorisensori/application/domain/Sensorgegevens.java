package com.lorisensori.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sensorgegevens")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"timestamp"}, allowGetters = true)
public class Sensorgegevens {

    @Id
    @Column
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
    
    
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date timestamp;

    @OneToOne()
    @JoinColumn(name = "slot_geopend_door")
    private Medewerker medewerkerSlot;

    public Sensorgegevens( 	int uplinkId,
    						int slotStatus,
    						int dieselniveau,
    						int accuniveau,
    						int vermogenZonnepaneel,
    						int gpsBreedtegraad,
    						int gpsBreedteMinuut,
    						int gpsBreedteSeconde,
    						int gpsBreedteTiendeSec,
    						int gpsLengtegraad,
    						int gpsLengteMinuut,
    						int gpsLengteSeconde,
    						int gpsLengteTiendeSec
    						) 
    
    {
    	this. uplinkId = uplinkId;
    	this. slotStatus = slotStatus;
    	this. dieselniveau = dieselniveau;
    	this. accuniveau = accuniveau;
    	this. vermogenZonnepaneel = vermogenZonnepaneel;
    	this. gpsBreedtegraad = gpsBreedtegraad;
    	this. gpsBreedteMinuut = gpsBreedteMinuut;
    	this. gpsBreedteSeconde = gpsBreedteSeconde;
    	this. gpsBreedteTiendeSec = gpsBreedteTiendeSec;
    	this. gpsLengtegraad = gpsLengtegraad;
    	this. gpsLengteMinuut = gpsLengteMinuut;
    	this. gpsLengteSeconde = gpsLengteSeconde;
    	this. gpsLengteTiendeSec = gpsLengteTiendeSec;
    }
    
    public Sensorgegevens() {
    }

///////////////////////////////////////////////////////////////////
    //GETTERS & SETTERS

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


    public double getDieselniveau() {
        return dieselniveau;
    }


    public void setDieselniveau(int dieselniveau) {
        this.dieselniveau = dieselniveau;
    }


    public double getAccuniveau() {
        return accuniveau;
    }


    public void setAccuniveau(int accuniveau) {
        this.accuniveau = accuniveau;
    }


    public double getVermogenZonnepaneel() {
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

	public int getGpsBreedteSeconde() {
		return gpsBreedteSeconde;
	}

	public void setGpsBreedteSeconde(int gpsBreedteSeconde) {
		this.gpsBreedteSeconde = gpsBreedteSeconde;
	}

	public int getGpsBreedteMinuut() {
		return gpsBreedteMinuut;
	}

	public void setGpsBreedteMinuut(int gpsBreedteMinuut) {
		this.gpsBreedteMinuut = gpsBreedteMinuut;
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

	public int getUplinkId() {
		return uplinkId;
	}

	public void setUplinkId(int uplinkId) {
		this.uplinkId = uplinkId;
	}

}
