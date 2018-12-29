	package com.lorisensori.application.domain;

	import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
	import org.springframework.data.annotation.CreatedDate;
	import org.springframework.data.jpa.domain.support.AuditingEntityListener;

	import javax.persistence.*;
	import java.io.Serializable;
	import java.util.Date;

	@Entity
	@Table(name = "sensorlog")
	@EntityListeners(AuditingEntityListener.class)
	@JsonIgnoreProperties(value = {"timestamp"}, allowGetters = true)

public class SensorLog {

	
	    /**
	     *Overleg met de rest wat to do aanpassen naar int oof uplink aanpassen nar opzet db?
	     */
	    private static final long serialVersionUID = 10L;

	    @Id
	    @Column(name = "sensor_id")
	    //private Long sensorId; // dan zou dit een String moeten worden is gelijk aan devId van TTN
	    private String sensor_Id; // zo dus
	    @Column(updatable = false)
	    /*
	    private boolean slotStatus;
	    private double dieselniveau;
	    private double accuniveau;
	    private double vermogenZonnepaneel;
	    private int gpsBreedtegraad;
	    private int gpsLengtegraad;
	    */
	    private String uplinkId; // int gemaakt
	    private String slotStatus; // int gemaakt
	    private String dieselniveau; // int gemaakt
	    private String accuniveau; // int gemaakt
	    private String vermogenZonnepaneel; // int gemaakt
	    private String gpsBreedtegraad;
	    private String gpsLengtegraad;
	    @Column(nullable = false)
	    @Temporal(TemporalType.TIMESTAMP)
	    @CreatedDate
	    private Date timestamp;

	    @OneToOne()
	    @JoinColumn(name = "slot_geopend_door")
	    private Medewerker medewerkerSlot;

	    public Sensorgegevens( String uplinkId, String sensor_Id, String slotStatus, String dieselniveau, String accuniveau, String vermogenZonnepaneel, String gpsBreedtegraad, String gpsLengtegraad) 
	    {
	    		
	    		this.uplinkId = uplinkId;
	    		this.sensor_Id = sensor_Id;   
	    		this.slotStatus = slotStatus;
	    		this.dieselniveau = dieselniveau;
	    		this.accuniveau = accuniveau;
	    		this.vermogenZonnepaneel = vermogenZonnepaneel;
	    		this.gpsBreedtegraad = gpsBreedtegraad;
	    		this.gpsLengtegraad = gpsLengtegraad;
	    }

	///////////////////////////////////////////////////////////////////
	    //GETTERS & SETTERS

	    public String getSensorId() {
	        return sensor_Id;
	    }


	   /* public void setSensorId(String sensorId) {
	        this.sensor_Id = sensorId;
	    }*/  // is niet updateable dus hoeft niet


	    public int isSlotStatus() {
	    	
	        return slotStatus;
	    }


	   /* public void setSlotStatus(int slotStatus) {
	        this.slotStatus = slotStatus;
	    }*/ //is niet updateable dus niet nodig


	    public double getDieselniveau() {
	        return dieselniveau;
	    }


	   /* public void setDieselniveau(int dieselniveau) {
	        this.dieselniveau = dieselniveau;
	    }*/ // is niet updateable dus niet nodig



	    public double getAccuniveau() {
	        return accuniveau;
	    }


	    /*public void setAccuniveau(int accuniveau) {
	        this.accuniveau = accuniveau;
	    }*/ //is niet updateable dus niet nodig



	    public double getVermogenZonnepaneel() {
	        return vermogenZonnepaneel;
	    }


	    /*public void setVermogenZonnepaneel(int vermogenZonnepaneel) {
	        this.vermogenZonnepaneel = vermogenZonnepaneel;
	    }*/ //is niet updateable dus niet nodig



	    public int getGpsBreedtegraad() {
	        return gpsBreedtegraad;
	    }


	    /*public void setGpsBreedtegraad(int gpsBreedtegraad) {
	        this.gpsBreedtegraad = gpsBreedtegraad;
	    }*/ //is niet updateable dus niet nodig



	    public int getGpsLengtegraad() {
	        return gpsLengtegraad;
	    }


	    /*public void setGpsLengtegraad(int gpsLengtegraad) {
	        this.gpsLengtegraad = gpsLengtegraad;
	    }*/ //is niet updateable dus niet nodig



	    public Date getTimestamp() {
	        return timestamp;
	    }

	}

	
}
