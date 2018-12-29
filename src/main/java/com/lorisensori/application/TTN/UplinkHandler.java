package com.lorisensori.application.TTN;


import org.springframework.data.annotation.CreatedDate;
import org.thethingsnetwork.data.common.Connection;
import org.thethingsnetwork.data.common.messages.ActivationMessage;
import org.thethingsnetwork.data.common.messages.DataMessage;
import org.thethingsnetwork.data.common.messages.DownlinkMessage;
import org.thethingsnetwork.data.common.messages.UplinkMessage;
import org.thethingsnetwork.data.mqtt.Client;

import com.lorisensori.application.domain.Sensorgegevens;
import com.lorisensori.application.enums.BerichtEnums;
import com.lorisensori.application.notificaties.Email;
import com.lorisensori.application.service.SensorgegevensService;

import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



public class UplinkHandler implements Runnable{
	
	//private TtnClient client = new TtnClient();
	private SensorgegevensService sensorservice;
	private TtnClient client;
	private UplinkMessage uplink;

    public UplinkHandler(TtnClient client, UplinkMessage uplink) {
    	
    	this.client = client;
    	this.uplink = uplink;
    	
    }

   public void ontvangBericht()
   {
	 
		  client.getClient().onMessage((String devId, DataMessage data) -> System.out.println("Message: " + devId + " " + Arrays.toString(((UplinkMessage) data).getPayloadRaw())));
		  client.getClient().onMessage((String _devId, DataMessage _data) -> {
	           
			 
	        });
	  
   }

   /* 
    * private boolean slotStatus;
    private double dieselniveau;
    private double accuniveau;
    private double vermogenZonnepaneel;
    private int gpsBreedtegraad;
    private int gpsLengtegraad;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date timestamp; (non-Javadoc)
    * @see java.lang.Runnable#run()
    */
   
   /*
    *Code maken om per uplink het bericht op te slaan  en alarmen door te zetten naar de mail
    * 
    */
   
   /*
    * 	TankUl	0x00	Alivebericht, word periodiek verstuurd	13
		ADieselniveauUl	0x01	Alarm dieselniveau te laag	2
		ADieselniveauUl	0x02	Alarm lekkage of diefstal	11
		AAccuniveauUl	0x03	Alarm accuniveau te laag	2
		WSlotstandUl	0x04	Alarm slotstand gewijzigd zonder opdracht	2
		AckDieselniveauW	0x05	Bevestiging dieselalarmniveau gewijzigd	1
		AcksluitingstijdW	0x06	Bevestiging sluitingstijd gewijzigd	1
		AckopeningstijdW	0x07	Evestiging openingstijd gewijzigd	1
		AckslotstandW	0x08	Bevestiging slotstand aangepast	1
		CheckBericht	0x09	Controlebericht wordt periodiek verstuurd	1
		AckWeekendSetting	0x10	Bevestiging weekendsetting gewijzigd	1

    */
   
   
   public Sensorgegevens saveAlivebericht (int[] alivebericht, String devId) { //kunnen we beter een int van maken
	   
	   
	   	String dev_id = devId;
	   	int uplinkId = alivebericht[0];
	   	int dieselniveau = alivebericht[1];
		int slotStatus = alivebericht[2];
		int accuniveau = alivebericht[3];
		int vermogenZonnepaneel = alivebericht[4] ;
		
		String breedtegraad = Integer.toString(alivebericht[5]) + Integer.toString(alivebericht[6]) + Integer.toString(alivebericht[7]) + Integer.toString(alivebericht[8]);
		
		int gpsBreedtegraad = Integer.parseInt(breedtegraad);
		
		String lengtegraad = Integer.toString(alivebericht[9]) + Integer.toString(alivebericht[10]) + Integer.toString(alivebericht[11]) + Integer.toString(alivebericht[12]);
		
		int gpsLengtegraad = Integer.parseInt(lengtegraad);
	   
	   
	   Sensorgegevens Sgegevens = new Sensorgegevens( uplinkId, dev_id, slotStatus, dieselniveau, accuniveau, vermogenZonnepaneel, gpsBreedtegraad, gpsLengtegraad);
	   
	   
	   return Sgegevens;
   }
   
  public Sensorgegevens saveAlarmniveau (int[] alarmniveau, String devId) { //kunnen we beter een int van maken
	   
	  
	  String dev_id = devId;
	  int uplinkId = alarmniveau[0];
	  int slotStatus = 0; // kunnen we hier ook null van maken??
	  int dieselniveau = alarmniveau[1];
	  int accuniveau = 0;
	  int vermogenZonnepaneel = 0;
	  int gpsBreedtegraad = 0;
	  int gpsLengtegraad = 0;
	  
	   Sensorgegevens Sgegevens = new Sensorgegevens(uplinkId, dev_id, slotStatus, dieselniveau, accuniveau, vermogenZonnepaneel, gpsBreedtegraad, gpsLengtegraad);
	   // TODO CODE om het dieselniveau uit een uplink te halen?
	   return Sgegevens;
   }
  
  public Sensorgegevens savediefstallekkage (int[] diefstallekkage, String devId) { //kunnen we beter een int van maken
	   
	  
	  String dev_id = devId;
	  int uplinkId = diefstallekkage[0];
	  int dieselniveau = diefstallekkage[1];
	  int slotStatus = diefstallekkage[2];
	  
	  int accuniveau = 0;
	  int vermogenZonnepaneel = 0;
	  
		String breedtegraad = Integer.toString(diefstallekkage[5]) + Integer.toString(diefstallekkage[6]) + Integer.toString(diefstallekkage[7]) + Integer.toString(diefstallekkage[8]);
		
		int gpsBreedtegraad = Integer.parseInt(breedtegraad);
		
		String lengtegraad = Integer.toString(diefstallekkage[9]) + Integer.toString(diefstallekkage[10]) + Integer.toString(diefstallekkage[11]) + Integer.toString(diefstallekkage[12]);
		
		int gpsLengtegraad = Integer.parseInt(lengtegraad);
	  
	   Sensorgegevens Sgegevens = new Sensorgegevens(uplinkId, dev_id, slotStatus, dieselniveau, accuniveau, vermogenZonnepaneel, gpsBreedtegraad, gpsLengtegraad);
	   // TODO CODE om het dieselniveau uit een uplink te halen?
	   return Sgegevens;
  }
  
  public Sensorgegevens saveAccuAlarm (int[] accuAlarm, String devId) { //kunnen we beter een int van maken
	   
	  
	  String dev_id = devId;
	  int uplinkId = accuAlarm[0];
	  int slotStatus = 0;
	  int dieselniveau = 0;
	  int accuniveau = accuAlarm[1];
	  int vermogenZonnepaneel = 0;
	  
		String breedtegraad = Integer.toString(accuAlarm[3]) + Integer.toString(accuAlarm[4]) + Integer.toString(accuAlarm[5]) + Integer.toString(accuAlarm[6]);
		
		int gpsBreedtegraad = Integer.parseInt(breedtegraad);
		
		String lengtegraad = Integer.toString(accuAlarm[7]) + Integer.toString(accuAlarm[8]) + Integer.toString(accuAlarm[9]) + Integer.toString(accuAlarm[10]);
		
		int gpsLengtegraad = Integer.parseInt(lengtegraad);
	  
	   Sensorgegevens Sgegevens = new Sensorgegevens(uplinkId, dev_id, slotStatus, dieselniveau, accuniveau, vermogenZonnepaneel, gpsBreedtegraad, gpsLengtegraad);
	   // TODO CODE om het dieselniveau uit een uplink te halen?
	   return Sgegevens;
  }
  
  public Sensorgegevens saveSlotstandAlarm (int[] slotstandAlarm, String devId) { //kunnen we beter een int van maken
	   
	  
	  String dev_id = devId;
	  int uplinkId = slotstandAlarm[0];
	  int slotStatus = slotstandAlarm[1];
	  int dieselniveau = 0;
	  int accuniveau = 0 ;
	  int vermogenZonnepaneel = 0;
	  int gpsBreedtegraad = 0;
	  int gpsLengtegraad = 0;
	  
	   Sensorgegevens Sgegevens = new Sensorgegevens(uplinkId, dev_id, slotStatus, dieselniveau, accuniveau, vermogenZonnepaneel, gpsBreedtegraad, gpsLengtegraad);
	   // TODO CODE om het dieselniveau uit een uplink te halen?
	   return Sgegevens;
  }
  
  public Sensorgegevens saveAckdieselniveauW (int[] AckniveauW, String devId) { //kunnen we beter een int van maken
	   
	  
	  String dev_id = devId;
	  int uplinkId = AckniveauW[0]; 
	  int slotStatus = 0;
	  int dieselniveau = 0;
	  int accuniveau = 0;
	  int vermogenZonnepaneel = 0;
	  int gpsBreedtegraad = 0;
	  int gpsLengtegraad = 0;
	  
	   Sensorgegevens Sgegevens = new Sensorgegevens(uplinkId, dev_id, slotStatus, dieselniveau, accuniveau, vermogenZonnepaneel, gpsBreedtegraad, gpsLengtegraad);
	   // TODO CODE om het dieselniveau uit een uplink te halen?
	   return Sgegevens;
  }
  
  public Sensorgegevens saveAckOpeningsTW (int[] openingsTW, String devId) { //kunnen we beter een int van maken
	   
	  
	  String dev_id = devId;
	  int uplinkId = openingsTW[0]; 
	  int slotStatus = 0;
	  int dieselniveau = 0;
	  int accuniveau = 0;
	  int vermogenZonnepaneel = 0;
	  int gpsBreedtegraad = 0;
	  int gpsLengtegraad = 0;
	  
	   Sensorgegevens Sgegevens = new Sensorgegevens(uplinkId, dev_id, slotStatus, dieselniveau, accuniveau, vermogenZonnepaneel, gpsBreedtegraad, gpsLengtegraad);
	   // TODO CODE om het dieselniveau uit een uplink te halen?
	   return Sgegevens;
  }
   
  public Sensorgegevens saveAckSluitingsTW (int[] sluitingsTW, String devId) { //kunnen we beter een int van maken
	   
	  
	  String dev_id = devId;
	  int uplinkId = sluitingsTW[0]; 
	  int slotStatus = 0;
	  int dieselniveau = 0;
	  int accuniveau = 0;
	  int vermogenZonnepaneel = 0;
	  int gpsBreedtegraad = 0;
	  int gpsLengtegraad = 0;
	  
	   Sensorgegevens Sgegevens = new Sensorgegevens(uplinkId, dev_id, slotStatus, dieselniveau, accuniveau, vermogenZonnepaneel, gpsBreedtegraad, gpsLengtegraad);
	   // TODO CODE om het dieselniveau uit een uplink te halen?
	   return Sgegevens;
 }
  
  public Sensorgegevens saveAckSlotstandW(int[] slotstandW, String devId) { //kunnen we beter een int van maken
	   
	  
	  String dev_id = devId;
	  int uplinkId = slotstandW[0]; 
	  int slotStatus = 0;
	  int dieselniveau = 0;
	  int accuniveau = 0;
	  int vermogenZonnepaneel = 0;
	  int gpsBreedtegraad = 0;
	  int gpsLengtegraad = 0;
	  
	   Sensorgegevens Sgegevens = new Sensorgegevens(uplinkId, dev_id, slotStatus, dieselniveau, accuniveau, vermogenZonnepaneel, gpsBreedtegraad, gpsLengtegraad);
	   // TODO CODE om het dieselniveau uit een uplink te halen?
	   return Sgegevens;
}
  
  public Sensorgegevens saveAckWeekendSettingW(int[] weekendsettingW, String devId) { //kunnen we beter een int van maken
	   
	  
	  String dev_id = devId;
	  int uplinkId = weekendsettingW[0]; 
	  int slotStatus = 0;
	  int dieselniveau = 0;
	  int accuniveau = 0;
	  int vermogenZonnepaneel = 0;
	  int gpsBreedtegraad = 0;
	  int gpsLengtegraad = 0;
	  
	   Sensorgegevens Sgegevens = new Sensorgegevens(uplinkId, dev_id, slotStatus, dieselniveau, accuniveau, vermogenZonnepaneel, gpsBreedtegraad, gpsLengtegraad);
	   // TODO CODE om het dieselniveau uit een uplink te halen?
	   return Sgegevens;
}
  
  public Sensorgegevens saveCheckBericht(int[] checkBericht, String devId) { //kunnen we beter een int van maken
	   
	  String dev_id = devId;
	  int uplinkId = checkBericht[0]; 
	  int slotStatus = 0;
	  int dieselniveau = 0;
	  int accuniveau = 0;
	  int vermogenZonnepaneel = 0;
	  int gpsBreedtegraad = 0;
	  int gpsLengtegraad = 0;
	  
	   Sensorgegevens Sgegevens = new Sensorgegevens(uplinkId, dev_id, slotStatus, dieselniveau, accuniveau, vermogenZonnepaneel, gpsBreedtegraad, gpsLengtegraad);
	   // TODO CODE om het dieselniveau uit een uplink te halen?
	   return Sgegevens;
}
    
   
  public void saveBericht(int[] bericht, String devId) 
  {
	  
	  if(bericht[0] == 0)
	  {
		  sensorservice.save(saveAlivebericht(bericht, devId));
	  }
	  if(bericht[0] == 1)
	  {
		  sensorservice.save(saveAlarmniveau(bericht, devId));
		  //Email.stuurBericht(ArrayList<String> emailTankBeheerders, BerichtEnums berichtType); stuur email als dit bericht komt
	  }
	  if(bericht[0] == 2)
	  {
		  sensorservice.save(savediefstallekkage(bericht, devId));
		//Email.stuurBericht(ArrayList<String> emailTankBeheerders, BerichtEnums berichtType); stuur email als dit bericht komt
	  }
	  if(bericht[0] == 3)
	  {
		  sensorservice.save(saveAccuAlarm(bericht, devId));
		//Email.stuurBericht(ArrayList<String> emailTankBeheerders, BerichtEnums berichtType); stuur email als dit bericht komt
	  }
	  if(bericht[0] == 4)
	  {
		  sensorservice.save(saveSlotstandAlarm(bericht, devId));
		//Email.stuurBericht(ArrayList<String> emailTankBeheerders, BerichtEnums berichtType); stuur email als dit bericht komt
	  }
	  if(bericht[0] == 5)
	  {
		  sensorservice.save(saveAckdieselniveauW(bericht, devId));
	  }
	  if(bericht[0] == 6)
	  {
		  sensorservice.save(saveAckSluitingsTW(bericht, devId));
	  }
	  if(bericht[0] == 7)
	  {
		  sensorservice.save(saveAckOpeningsTW(bericht, devId));
	  }
	  if(bericht[0] == 8)
	  {
		  sensorservice.save(saveAckSlotstandW(bericht, devId));
	  }
	  if(bericht[0] == 9)
	  {
		  sensorservice.save(saveAckWeekendSettingW(bericht, devId));
	  }
	  if(bericht[0] == 10)
	  {
		  sensorservice.save(saveCheckBericht(bericht, devId));
	  }
	   
  }

 @Override
public void run() {
		
		System.out.println("ik ga proberen een berichtje te vangen");
		ontvangBericht();
		saveBericht(uplink.getPayloadRaw(), uplink.getDevId());
		System.out.println("gelukt?");
	
}
}

