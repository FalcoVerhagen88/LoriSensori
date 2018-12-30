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

	    private String sensor_Id; // zo dus
	    @Column(updatable = false)

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
	    
	    
	    @ManyToOne()
	    @JoinColumn(name = "sensorlog")
	    private Sensorgegevens sensorgegevens;
	    
	    
	    //@OneToOne()
	    //@JoinColumn(name = "slot_geopend_door")
	    //private Medewerker medewerkerSlot;
	    
	    

	    

	    
	    public SensorLog( String uplinkId, String sensor_Id, String slotStatus, String dieselniveau, String accuniveau, String vermogenZonnepaneel, String gpsBreedtegraad, String gpsLengtegraad) 
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
	    
	    
	    // empty constructor for spring boot
	    public SensorLog() {};

	///////////////////////////////////////////////////////////////////
	    //GETTERS & SETTERS

	    public String getSensorId() {
	        return sensor_Id;
	    }
	    
	    public void setSensorId(String sensorId)
	    {
	    	this.sensor_Id = sensorId;
	    }


	    public String getSlotStatus() {
	    	
	        return slotStatus;
	    }
	    
	    public void setSlotstatus(String slotstatus)
	    {
	    	this.slotStatus = slotstatus;
	    }
	    
	    
	    public String getDieselniveau() {
	        return dieselniveau;
	    }

	    
	    public void setDieselniveau(String dieselniveau)
	    {
	    	this.dieselniveau = dieselniveau;
	    }

	    public String getAccuniveau() {
	        return accuniveau;
	    }

	    public void setAccuniveau(String accuniveau) 
	    {
	    	this.accuniveau = accuniveau;
	    }



	    public String getVermogenZonnepaneel() {
	        return vermogenZonnepaneel;
	    }


	    public void setVermogenZonnepanel(String vermogen)
	    {
	    	this.vermogenZonnepaneel = vermogen;
	    }



	    public String getGpsBreedtegraad() {
	        return gpsBreedtegraad;
	    }


	    public void setGpsBreedtegraad(String breedtegraad)
	    {
	    	this.gpsBreedtegraad = breedtegraad;
	    }


	    public String getGpsLengtegraad() {
	        return gpsLengtegraad;
	    }


	    public void setGpsLengtegraad(String lengtegraad)
	    {
	    	this.gpsLengtegraad = lengtegraad;
	    }

	    public Date getTimestamp() {
	        return timestamp;
	    }

	}

