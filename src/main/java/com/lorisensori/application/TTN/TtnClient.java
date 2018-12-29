package com.lorisensori.application.TTN;


import org.thethingsnetwork.data.common.Connection;
import org.thethingsnetwork.data.common.messages.ActivationMessage;
import org.thethingsnetwork.data.common.messages.DataMessage;
import org.thethingsnetwork.data.common.messages.DownlinkMessage;
import org.thethingsnetwork.data.common.messages.UplinkMessage;
import org.thethingsnetwork.data.mqtt.Client;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Base64;



public class TtnClient {
	
	private static final String REGION = "eu";
    private static final String APP_ID = "tanks_lorisensori";
    private static final String ACCESS_KEY = "ttn-account-v2.S4DKj7oir_lt9lLyXg_3yZU-UDdVkzlDgZfnoIFzbec";
    
    private static Client CLIENT;
	
    static {
    	try {
    		CLIENT = new Client(REGION, APP_ID, ACCESS_KEY);
    	}
    	catch(URISyntaxException e){e.printStackTrace();}
    }
    public TtnClient() {}
    
    public Client getClient() 
    {
    	return CLIENT;
    }
  /* public Client ttnClient() throws URISyntaxException {
    	
    	Client gebruiker = new Client(REGION, APP_ID, ACCESS_KEY);
    	return gebruiker;

    }
    
    
    public void ttnClientStart()
    {
    	
    	try 
    	{
    	//Client gebruiker = new Client(REGION, APP_ID, ACCESS_KEY);
    	//gebruiker.start();
    	//gebruiker.onConnected((Connection _client) -> System.out.println("connected !"));
    	ttnClient().start();
    	}
    	catch (Exception e)
    	{
    		e.printStackTrace();
    	}
    }*/
    
}




