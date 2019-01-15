package com.lorisensori.application.TTN;

import org.thethingsnetwork.data.common.Connection;
import org.thethingsnetwork.data.common.messages.ActivationMessage;
import org.thethingsnetwork.data.common.messages.DataMessage;
import org.thethingsnetwork.data.common.messages.DownlinkMessage;
import org.thethingsnetwork.data.common.messages.RawMessage;
import org.thethingsnetwork.data.common.messages.UplinkMessage;
import org.thethingsnetwork.data.mqtt.Client;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Base64;

//Run application with: mvn clean compile exec:java -Dexec.mainClass="com.lorisensori.application.TTN.TheThingsNetwork"

/*
 * TODO:
 * uitzoeken hoe per klant in te loggen bij TTN
 * uitzoeken hoe per klant de berichten opgehaald kunnen worden
 * er zal wel multi threading moeten worden toegepast
 * hoe moet dat dan opgebouwd worden?
 *
 *een thread starten per bedrijf dat tanks heeft bij het starten van de server
 *tanks gegevens ophalen van bedrijf
 *zorgen dat elke tank apart kan worden aangesproken
 *zorgen dat gegevens per tank goed worden opgeslagen in de db
 *zorgen dat ack berichten naar de applicatie doorkomen
 *
 *Start automatisch op als er een bericht binnenkomt, daarom message weg gecomment
 *
 */

public class TheThingsNetwork {

    private static final String REGION = "eu";
    private static final String APP_ID = "tanks_lorisensori";
    private static final String ACCESS_KEY = "ttn-account-v2.S4DKj7oir_lt9lLyXg_3yZU-UDdVkzlDgZfnoIFzbec";
    private static byte[] payload;
    private static Client CLIENT;


    static {
        try {
            CLIENT = new Client(REGION, APP_ID, ACCESS_KEY);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public TheThingsNetwork() {
    }

    public static void setpayload(byte[] payload) {
        TheThingsNetwork.payload = payload;
    }

    public static byte[] getpayload() {
        return TheThingsNetwork.payload;
    }

    public static void main(String[] args) throws Exception {

        int counter = 0;
//        Client client = new Client(REGION, APP_ID, ACCESS_KEY);


//        CLIENT.onError((Throwable _error) -> System.err.println("error: " + _error.getMessage()));

//        CLIENT.onConnected((Connection _client) -> System.out.println("connected !"));

        // CLIENT.onActivation((String _devId, ActivationMessage _data) ->
        // System.out.println("Activation: " + _devId + ", data: " + _data));

        // CLIENT.onMessage((String _devId, DataMessage _data) ->
        // System.out.println("Message: " + _devId + " " + _data));
        // CLIENT.onMessage((String devId, DataMessage data) -> System.out.println("Message: " + devId + " " + Arrays.toString(((UplinkMessage) data).getPayloadRaw())));

//        CLIENT.onMessage((String devId, DataMessage data) -> {

//            try {
				  
				/*
				  payload = new byte[((UplinkMessage) data).getPayloadRaw().length];
					for(int i = 0; i < ((UplinkMessage) data).getPayloadRaw().length; i++)
					{
						payload[i] = ((UplinkMessage) data).getPayloadRaw()[i];
					}
					
					setpayload(payload);
					
					   for(int i = 0; i < getpayload().length; i++ )
				        {
				        	System.out.print("Waarde van de " + i + " byte is : ");
				        	System.out.print(getpayload()[i]);
				        	System.out.println("");
				        }
					   */

//                System.out.print("Message: " + devId); // zo werkt het, dat scheelt weer
//                System.out.println(" ");
//                for (int i = 0; i < ((UplinkMessage) data).getPayloadRaw().length; i++) {
//                    System.out.print("Waarde van de " + i + " byte is : ");
//                    System.out.print(((UplinkMessage) data).getPayloadRaw()[i]);
//                    System.out.println("");
//
//                }
//
//                System.out.println("einde bericht");
//                System.out.print(" ");
//
//            } catch (Exception ex) {
//                System.out.println("Response failed: " + ex.getMessage());
//            }
//        });


        //  CLIENT.onMessage((String devId, DataMessage data) ->
//        CLIENT.onMessage((String _devId, DataMessage _data) -> {
//            try {
                // Toggle the LED
//                DownlinkMessage response = new DownlinkMessage(1, new byte[]{0x00, 0x01});
//
//                System.out.println("Sending: " + response);
//                CLIENT.send(_devId, response);
//            } catch (Exception ex) {
//                System.out.println("Response failed: " + ex.getMessage());
//            }
//        });

    }

    public static void testDecoder() throws Exception {
        byte[] key = new byte[]{0x2B, 0x7E, 0x15, 0x16, 0x28, (byte) 0xAE, (byte) 0xD2, (byte) 0xA6, (byte) 0xAB, (byte) 0xF7, 0x15, (byte) 0x88, 0x09, (byte) 0xCF, 0x4F, 0x3C};
        byte[] iv = new byte[]{0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0};

        String message = "thisisverysecret";

        byte[] encrypted = Crypto.encryptAES(message.getBytes(), key, iv);
        String encoded = Base64.getEncoder().encodeToString(encrypted);
        String decrypted = Crypto.decrypt(encoded, key, iv);

        System.out.println(decrypted);
    }
}
