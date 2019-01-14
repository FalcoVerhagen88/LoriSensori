package com.lorisensori.application.TTN;


import com.lorisensori.application.service.TankService;
import org.springframework.data.annotation.CreatedDate;
import org.thethingsnetwork.data.common.Connection;
import org.thethingsnetwork.data.common.events.UplinkHandler;
import org.thethingsnetwork.data.common.messages.ActivationMessage;
import org.thethingsnetwork.data.common.messages.DataMessage;
import org.thethingsnetwork.data.common.messages.DownlinkMessage;
import org.thethingsnetwork.data.common.messages.RawMessage;
import org.thethingsnetwork.data.common.messages.UplinkMessage;
import org.thethingsnetwork.data.mqtt.Client;

import com.lorisensori.application.domain.SensorLog;
import com.lorisensori.application.domain.Sensorgegevens;
import com.lorisensori.application.enums.BerichtEnums;
import com.lorisensori.application.notificaties.Email;
import com.lorisensori.application.service.SensorgegevensService;
import com.lorisensori.application.service.TankServiceImpl;

import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



public class TtnUplinkHandler extends RawMessage implements Runnable{
	
	//private TtnClient client = new TtnClient();
	private SensorgegevensService sensorservice;
	private Client client;
	private byte[] payload;
	private TankService tankService;
	private DataMessage uplink;
	private String devId;
	private final byte ALIVEBERICHTID = 0;
	//private final int ALIVEBERICHTLENGTE = 13; //berichtlengtes ook final int van maken
	private final byte DIESELALARMNINEAUID = 1;
	//private final int DIESELALARMNIVEAULENGTE = 2; //berichtlengtes ook final int van maken
	private final byte DIEFSTALALARMID = 2;
	//private final int DIEFSTALALARMLENGTE = 11; //berichtlengtes ook final int van maken
	private final byte ACCUNIVEAUID = 3;
	//private final int ACCUNIVEAULENGTE = 2; //berichtlengtes ook final int van maken
	private final byte SLOTSTANDALARMID = 4;
	//private final int SLOTSTANDALARMLENGTE = 2; //berichtlengtes ook final int van maken
	private final byte ACKDIESELNIVEAUID = 5;
	//private final int ACKDIESELNIVEAULENGTE = 1; //berichtlengtes ook final int van maken
	private final byte ACKSLUITINGSTIJDWID = 6;
	//private final int ACKSLUITINGSTIJDWIDLENGTE = 1; //berichtlengtes ook final int van maken
	private final byte ACKOPENINGSTIJDWID = 7;
	//private final int ACKOPENINGSTIJDWIDLENGTE = 1; //berichtlengtes ook final int van maken
	private final byte ACKSLOTSTANDWID = 8;
	//private final int ACKSLOTSTANDWIDLENGTE = 1; //berichtlengtes ook final int van maken
	private final byte ACKWEEKENDSETTINGID = 10;
	//private final int ACKWEEKENDSETTINGIDLENGTE = 1; //berichtlengtes ook final int van maken
	private final byte CHECKBERICHTID = 9;
	//private final int CHECKBERICHTIDLENGTE = 1; //berichtlengtes ook final int van maken

    public TtnUplinkHandler(Client client, DataMessage uplink, String devId) {
    	
    	this.client = client;
    	this.uplink = uplink;


    }

    public void setpayload(byte[] payload)
    {
    	this.payload = payload;
    }
    
   public void ontvangBericht(String devId, DataMessage data)
   {
	   	  // test print om te kijken of er wel iets in de array staat
			  try
			  {	
				  switch (((UplinkMessage) data).getPayloadRaw()[0])
					  {
				  case ALIVEBERICHTID :
					  saveAlivebericht(((UplinkMessage) data).getPayloadRaw(), devId);
					  System.out.println("\n" + "alivebericht");
					  break;
				  case DIESELALARMNINEAUID :
					  saveDieselniveauAlarm(((UplinkMessage) data).getPayloadRaw(), devId);
					  System.out.println("\n" + "DieselAlarmniveau");
					  break;
				  case DIEFSTALALARMID :
					  saveDiefstalAlarm(((UplinkMessage) data).getPayloadRaw(), devId);
					  System.out.println("\n" + "DiefstalAlarm");
					  break;
				  case ACCUNIVEAUID :
					  saveAccuAlarm(((UplinkMessage) data).getPayloadRaw(), devId);
					  System.out.println("\n" + "Accuniveau");
					  break;
				  case SLOTSTANDALARMID :
					  saveSlotstandAlarm(((UplinkMessage) data).getPayloadRaw(), devId);
					  System.out.println("\n SlotstandAlarm");
					  break;
				  case ACKDIESELNIVEAUID :
					  saveAckDieselniveau(((UplinkMessage) data).getPayloadRaw(), devId);
					  System.out.println("\n" + "wijziging dieselniveau");
					  break;
				  case ACKSLUITINGSTIJDWID :
					  saveAckSluitingstijdW(((UplinkMessage) data).getPayloadRaw(), devId);
					  System.out.println("\n" + "wijziging sluitingstijd");
					  break;
				  case ACKOPENINGSTIJDWID :
					  saveAckOpeningstijdW(((UplinkMessage) data).getPayloadRaw(), devId);
					  System.out.println("\n" + "wijziging openingstijd");
					  break;
				  case ACKSLOTSTANDWID :
					  saveAckSlotstand(((UplinkMessage) data).getPayloadRaw(), devId);
					  System.out.println("\n" + "wijziging slotstand");
					  break;
				  case ACKWEEKENDSETTINGID :
					  saveAckWeekendSetting(((UplinkMessage) data).getPayloadRaw(), devId);
					  System.out.println("\n" + "wijziging weekendsetting");
					  break;
				  case CHECKBERICHTID :
					  saveCheckBericht(((UplinkMessage) data).getPayloadRaw(), devId);
					  System.out.println("\n" + "checkbericht");
					  break;
					  }
			
			  }
			  catch(Exception ex)
			  {
				  System.out.println("Response failed: " + ex.getMessage());  
			  }
	}
	

   
   
   public void saveAlivebericht (byte[] alivebericht, String devId) {  // bericht en devid gaan mee, sensorgegevens worden opgeslagen in list in tank
	   
	   try {
	   tankService.findByDevId(devId).addSensorGegevens( new Sensorgegevens(Byte.toUnsignedInt(alivebericht[0]), //this.uplinkId;
			   																Byte.toUnsignedInt(alivebericht[2]), //this.slotStatus = slotStatus;
   																			Byte.toUnsignedInt(alivebericht[1]),//this. dieselniveau = dieselniveau;
   																			Byte.toUnsignedInt(alivebericht[3]),//this. accuniveau = accuniveau;
   																			Byte.toUnsignedInt(alivebericht[4]),//this. vermogenZonnepaneel = vermogenZonnepaneel;
   																			Byte.toUnsignedInt(alivebericht[5]),//this. gpsBreedtegraad = gpsBreedtegraad;
   																			Byte.toUnsignedInt(alivebericht[6]),//this. gpsBreedteMinuut = gpsBreedteMinuut;
   																			Byte.toUnsignedInt(alivebericht[7]),//this. gpsBreedteSeconde = gpsBreedteSeconde;
   																			Byte.toUnsignedInt(alivebericht[8]),//this. gpsBreedteTiendeSec = gpsBreedteTiendeSec;
   																			Byte.toUnsignedInt(alivebericht[9]),//this. gpsLengtegraad = gpsLengtegraad;
   																			Byte.toUnsignedInt(alivebericht[10]),//this. gpsLengteMinuut = gpsLengteMinuut;
   																			Byte.toUnsignedInt(alivebericht[11]),//this. gpsLengteSeconde = gpsLengteTiendeSec;
   																			Byte.toUnsignedInt(alivebericht[12])//this. gpsLengteTiendeSec = gpsLengteTiendeSec;
			   																)); // sla het bericht op in de lijst met sensorgegevens in tank gezocht op tankId
	   
	 tankService.save(tankService.findByDevId(devId)); // sla de tank met de aangepaste lijst op, gezocht op devID. devId is de benaming zoals gegeven op TTN
   }
	catch(NullPointerException enull)
	{System.out.println("verkeerde verwijzing naar array");}
	catch(Exception e) 
	{System.out.println("er is iets mis gegaan");}
	 
   }
   
   public void saveDieselniveauAlarm (byte[] alarmAndAckBericht, String devId) { //kunnen we beter een int van maken
	   
	   try {
	   		tankService.findByDevId(devId).addSensorLog( new SensorLog(	Byte.toString(alarmAndAckBericht[0]),//String uplinkId
																		Byte.toString(alarmAndAckBericht[1]),//String slotStatus 
																		null,								//String dieselniveau
																		null,								//String accuniveau
																		null,								//String vermogenZonnepaneel
																		null,								//String gpsBreedtegraad
																		null,								//String gpsBreedteMinuut
																		null,								//String gpsBreedteSeconde
																		null,								//String gpsBreedteTiendeSec
																		null,								//String gpsLengtegraad
																		null,								//String gpsLengteMinuut
																		null,								//String gpsLengteSeconde
																		null,								//String gpsLengteTiendeSec
																		null								// String weekendsetting
																		));

	   		tankService.save(tankService.findByDevId(devId)); // sla de tank met de aangepaste lijst op, gezocht op devID. devId is de benaming zoals gegeven op TTN
	   }
	catch(NullPointerException enull)
	{System.out.println("verkeerde verwijzing naar array");}
	catch(Exception e) 
	{System.out.println("er is iets mis gegaan");}
}
   
   public void saveDiefstalAlarm (byte[] alarmAndAckBericht, String devId) { //kunnen we beter een int van maken
	   
	   
  		try {
  		tankService.findByDevId(devId).addSensorLog( new SensorLog(	Byte.toString(alarmAndAckBericht[0]),//String uplinkId
  																	Byte.toString(alarmAndAckBericht[2]),//String slotStatus 
  																	Byte.toString(alarmAndAckBericht[1]),//String dieselniveau
  																	null,								 //String accuniveau
  																	null,								 //String vermogenZonnepaneel
  																	Byte.toString(alarmAndAckBericht[3]),//String gpsBreedtegraad
  																	Byte.toString(alarmAndAckBericht[4]),//String gpsBreedteMinuut
  																	Byte.toString(alarmAndAckBericht[5]),//String gpsBreedteSeconde
  																	Byte.toString(alarmAndAckBericht[6]),//String gpsBreedteTiendeSec
  																	Byte.toString(alarmAndAckBericht[7]),//String gpsLengtegraad
  																	Byte.toString(alarmAndAckBericht[8]),//String gpsLengteMinuut
  																	Byte.toString(alarmAndAckBericht[9]),//String gpsLengteSeconde
  																	Byte.toString(alarmAndAckBericht[10]), //String gpsLengteTiendeSec
  																	null								// String weekendsetting
																));
  		tankService.save(tankService.findByDevId(devId)); // sla de tank met de aangepaste lijst op, gezocht op devID. devId is de benaming zoals gegeven op TTN
  		}
 		catch(NullPointerException enull)
 		{System.out.println("verkeerde verwijzing naar array");}
 		catch(Exception e) 
 		{System.out.println("er is iets mis gegaan");}
  
}

   public void saveAccuAlarm (byte[] alarmAndAckBericht, String devId) { //kunnen we beter een int van maken
	   
 	try {
 		tankService.findByDevId(devId).addSensorLog( new SensorLog(	Byte.toString(alarmAndAckBericht[0]),//String uplinkId
 																	null								,//String slotStatus 
 																	null,								 //String dieselniveau
 																	Byte.toString(alarmAndAckBericht[1]),//String accuniveau
 																	null,								//String vermogenZonnepaneel
 																	Byte.toString(alarmAndAckBericht[2]),//String gpsBreedtegraad
 																	Byte.toString(alarmAndAckBericht[3]),//String gpsBreedteMinuut
 																	Byte.toString(alarmAndAckBericht[4]),//String gpsBreedteSeconde
 																	Byte.toString(alarmAndAckBericht[5]),//String gpsBreedteTiendeSec
 																	Byte.toString(alarmAndAckBericht[6]),//String gpsLengtegraad
 																	Byte.toString(alarmAndAckBericht[7]),//String gpsLengteMinuut
 																	Byte.toString(alarmAndAckBericht[8]),//String gpsLengteSeconde
 																	Byte.toString(alarmAndAckBericht[9]), //String gpsLengteTiendeSec
 																	null								// String weekendsetting
							));
 		tankService.save(tankService.findByDevId(devId)); // sla de tank met de aangepaste lijst op, gezocht op devID. devId is de benaming zoals gegeven op TTN
 		}
 		catch(NullPointerException enull)
 		{System.out.println("verkeerde verwijzing naar array");}
 		catch(Exception e) 
 		{System.out.println("er is iets mis gegaan");}
}
   
   public void saveSlotstandAlarm (byte[] alarmAndAckBericht, String devId) { //kunnen we beter een int van maken
	   
 		try {
 		tankService.findByDevId(devId).addSensorLog( new SensorLog(	Byte.toString(alarmAndAckBericht[0]),//String uplinkId
 																	Byte.toString(alarmAndAckBericht[1]),//String slotStatus 
 																	null,								//String dieselniveau
 																	null,								//String accuniveau
 																	null,								//String vermogenZonnepaneel
 																	null,								//String gpsBreedtegraad
 																	null,								//String gpsBreedteMinuut
 																	null,								//String gpsBreedteSeconde
 																	null,								//String gpsBreedteTiendeSec
 																	null,								//String gpsLengtegraad
 																	null,								//String gpsLengteMinuut
 																	null,								//String gpsLengteSeconde
 																	null,								//String gpsLengteTiendeSec
 																	null								// String weekendsetting
																));
 		tankService.save(tankService.findByDevId(devId)); // sla de tank met de aangepaste lijst op, gezocht op devID. devId is de benaming zoals gegeven op TTN
 		}
 		catch(NullPointerException enull)
 		{System.out.println("verkeerde verwijzing naar array");}
 		catch(Exception e) 
 		{System.out.println("er is iets mis gegaan");}
}
   
   public void saveAckDieselniveau (byte[] alarmAndAckBericht, String devId) { //kunnen we beter een int van maken
	   
 		try {
 		tankService.findByDevId(devId).addSensorLog( new SensorLog(	Byte.toString(alarmAndAckBericht[0]),//String uplinkId
 																	null,								 //String slotStatus 
 																	Byte.toString(alarmAndAckBericht[1]),//String dieselniveau
 																	null,								//String accuniveau
 																	null,								//String vermogenZonnepaneel
 																	null,								//String gpsBreedtegraad
 																	null,								//String gpsBreedteMinuut
 																	null,								//String gpsBreedteSeconde
 																	null,								//String gpsBreedteTiendeSec
 																	null,								//String gpsLengtegraad
 																	null,								//String gpsLengteMinuut
 																	null,								//String gpsLengteSeconde
 																	null,								//String gpsLengteTiendeSec
 																	null								// String weekendsetting
																));
 		tankService.save(tankService.findByDevId(devId)); // sla de tank met de aangepaste lijst op, gezocht op devID. devId is de benaming zoals gegeven op TTN
 		}
 		catch(NullPointerException enull)
 		{System.out.println("verkeerde verwijzing naar array");}
 		catch(Exception e) 
 		{System.out.println("er is iets mis gegaan");}
 	}
   
   public void saveAckSluitingstijdW (byte[] alarmAndAckBericht, String devId) { //kunnen we beter een int van maken
	   
 		try {
 		tankService.findByDevId(devId).addSensorLog( new SensorLog(Byte.toString(alarmAndAckBericht[0]),//String uplinkId
 																	null,								//String slotStatus 
 																	null,								//String dieselniveau
 																	null,								//String accuniveau
 																	null,								//String vermogenZonnepaneel
 																	null,								//String gpsBreedtegraad
 																	null,								//String gpsBreedteMinuut
 																	null,								//String gpsBreedteSeconde
 																	null,								//String gpsBreedteTiendeSec
 																	null,								//String gpsLengtegraad
 																	null,								//String gpsLengteMinuut
 																	null,								//String gpsLengteSeconde
 																	null,								//String gpsLengteTiendeSec
 																	null								// String weekendsetting
																));
 		tankService.save(tankService.findByDevId(devId)); // sla de tank met de aangepaste lijst op, gezocht op devID. devId is de benaming zoals gegeven op TTN
 		}
 		catch(NullPointerException enull)
 		{System.out.println("verkeerde verwijzing naar array");}
 		catch(Exception e) 
 		{System.out.println("er is iets mis gegaan");}
 }
   
   public void saveAckOpeningstijdW (byte[] alarmAndAckBericht, String devId) { //kunnen we beter een int van maken
	   
 		try {
 		tankService.findByDevId(devId).addSensorLog( new SensorLog(	Byte.toString(alarmAndAckBericht[0]),//String uplinkId
 																	null,								//String slotStatus 
 																	null,								//String dieselniveau
 																	null,								//String accuniveau
 																	null,								//String vermogenZonnepaneel
 																	null,								//String gpsBreedtegraad
 																	null,								//String gpsBreedteMinuut
 																	null,								//String gpsBreedteSeconde
 																	null,								//String gpsBreedteTiendeSec
 																	null,								//String gpsLengtegraad
 																	null,								//String gpsLengteMinuut
 																	null,								//String gpsLengteSeconde
 																	null,								//String gpsLengteTiendeSec
 																	null								// String weekendsetting
																));
 		tankService.save(tankService.findByDevId(devId)); // sla de tank met de aangepaste lijst op, gezocht op devID. devId is de benaming zoals gegeven op TTN
 		}
 		catch(NullPointerException enull)
 		{System.out.println("verkeerde verwijzing naar array");}
 		catch(Exception e) 
 		{System.out.println("er is iets mis gegaan");}
}
   
   public void saveAckSlotstand (byte[] alarmAndAckBericht, String devId) { //kunnen we beter een int van maken
	   
 		try {
 		tankService.findByDevId(devId).addSensorLog( new SensorLog(	Byte.toString(alarmAndAckBericht[0]),//String uplinkId
 																	null,								//String slotStatus 
 																	null,								//String dieselniveau
 																	null,								//String accuniveau
 																	null,								//String vermogenZonnepaneel
 																	null,								//String gpsBreedtegraad
 																	null,								//String gpsBreedteMinuut
 																	null,								//String gpsBreedteSeconde
 																	null,								//String gpsBreedteTiendeSec
 																	null,								//String gpsLengtegraad
 																	null,								//String gpsLengteMinuut
 																	null,								//String gpsLengteSeconde
 																	null,								//String gpsLengteTiendeSec
 																	null								// String weekendsetting
																));
 		tankService.save(tankService.findByDevId(devId)); // sla de tank met de aangepaste lijst op, gezocht op devID. devId is de benaming zoals gegeven op TTN
 		}
 		catch(NullPointerException enull)
 		{System.out.println("verkeerde verwijzing naar array");}
 		catch(Exception e) 
 		{System.out.println("er is iets mis gegaan");}
 	}
   
   public void saveAckWeekendSetting (byte[] alarmAndAckBericht, String devId) { //kunnen we beter een int van maken
	   
 		try {
 		tankService.findByDevId(devId).addSensorLog( new SensorLog(	Byte.toString(alarmAndAckBericht[0]),//String uplinkId
 																	null,								//String slotStatus 
 																	null,								//String dieselniveau
 																	null,								//String accuniveau
 																	null,								//String vermogenZonnepaneel
 																	null,								//String gpsBreedtegraad
 																	null,								//String gpsBreedteMinuut
 																	null,								//String gpsBreedteSeconde
 																	null,								//String gpsBreedteTiendeSec
 																	null,								//String gpsLengtegraad
 																	null,								//String gpsLengteMinuut
 																	null,								//String gpsLengteSeconde
 																	null,								//String gpsLengteTiendeSec
 																	Byte.toString(alarmAndAckBericht[1])								// String weekendsetting
																));
 		tankService.save(tankService.findByDevId(devId)); // sla de tank met de aangepaste lijst op, gezocht op devID. devId is de benaming zoals gegeven op TTN
   }
	catch(NullPointerException enull)
	{System.out.println("verkeerde verwijzing naar array");}
	catch(Exception e) 
	{System.out.println("er is iets mis gegaan");}
}
   
   public void saveCheckBericht (byte[] alarmAndAckBericht, String devId) { //kunnen we beter een int van maken

	   
 		try {
 		tankService.findByDevId(devId).addSensorLog( new SensorLog(	Byte.toString(alarmAndAckBericht[0]),//String uplinkId
 																	null,								//String slotStatus 
 																	null,								//String dieselniveau
 																	null,								//String accuniveau
 																	null,								//String vermogenZonnepaneel
 																	null,								//String gpsBreedtegraad
 																	null,								//String gpsBreedteMinuut
 																	null,								//String gpsBreedteSeconde
 																	null,								//String gpsBreedteTiendeSec
 																	null,								//String gpsLengtegraad
 																	null,								//String gpsLengteMinuut
 																	null,								//String gpsLengteSeconde
 																	null,								//String gpsLengteTiendeSec
 																	null								//String weekendsetting
																));
 		tankService.save(tankService.findByDevId(devId)); // sla de tank met de aangepaste lijst op, gezocht op devID. devId is de benaming zoals gegeven op TTN
 		}
 		catch(NullPointerException enull)
 		{System.out.println("verkeerde verwijzing naar array");}
 		catch(Exception e) 
 		{System.out.println("er is iets mis gegaan");}
 		
}

 @Override
public void run() {
		
		System.out.println("ik ga proberen een berichtje te vangen");
		ontvangBericht(devId, uplink);
		System.out.println("gelukt?");
	
}

@Override
public String asString() {
	// TODO Auto-generated method stub
	return null;
}

public void setUplinkMessage(DataMessage uplink)
{
	this.uplink = uplink;
}

public void setdevId(String devId)
{
	this.devId = devId;
}

	public void setClient(Client client) {
    	this.client = client;
	}
}
