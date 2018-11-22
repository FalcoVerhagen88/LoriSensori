package com.lorisensori.application.notificaties;

import com.lorisensori.application.enums.BerichtEnums;
import com.lorisensori.application.logic.Medewerker;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.util.ArrayList;

public class SMS extends Bericht
{
	
	public static final String ACCOUNT_SID = "";
	public static final String AUTH_TOKEN = "";
	public static final String PHONE_NUMBER = "";
	
	public static void main(String[]args) 
	{
	    SMS sms = new SMS();
	    ArrayList <String> telefoonNummers = new ArrayList<>();
	    BerichtEnums berichtType = BerichtEnums.ONGEWENSTE_NIVEAU_DALING;
	    
	    sms.stuurBericht(telefoonNummers, berichtType);
	}
	

	public void stuurBericht(ArrayList<String> telefoonNrTankBeheerders, BerichtEnums berichtType)
	{
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		for(String telefoonNummer : telefoonNrTankBeheerders) 
		{
			Message message = Message.creator(new PhoneNumber(telefoonNummer), new PhoneNumber(PHONE_NUMBER) , berichtType.toString() +
					" ER HEEFT EEN NIVEAUDALING BUITEN OPENINGSTIJDEN PLAATSGEVONDEN!!!").create();
			
			System.out.println(message.getSid());
		}
	}

	@Override
	public boolean medewerkerGeupdate(Medewerker medewerker) {
		return false;
	}
}
