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

//Run application with: mvn clean compile exec:java -Dexec.mainClass="com.groep2.lorisensori.TTNclient.TheThingsNetwork"

public class TheThingsNetwork {

    private static final String REGION = System.getenv("REGION");
    private static final String APP_ID = System.getenv("APP_ID");
    private static final String ACCESS_KEY = System.getenv("ACCESS_KEY");

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

    public static void main(String[] args) throws Exception {


        Client client = new Client(REGION, APP_ID, ACCESS_KEY);


        client.onError((Throwable _error) -> System.err.println("error: " + _error.getMessage()));

        client.onConnected((Connection _client) -> System.out.println("connected !"));

        client.onActivation((String _devId, ActivationMessage _data) ->
                System.out.println("Activation: " + _devId + ", data: " + _data));

        client.onMessage((String _devId, DataMessage _data) ->
                System.out.println("Message: " + _devId + " " + _data));

        client.onMessage((String devId, DataMessage data) -> System.out.println("Message: " + devId + " " + Arrays.toString(((UplinkMessage) data).getPayloadRaw())));
        client.onMessage((String _devId, DataMessage _data) -> {
            try {
                // Toggle the LED
                DownlinkMessage response = new DownlinkMessage(1, new byte[]{0x00, 0x01});

                /**
                 * If you don't have an encoder payload function:
                 * client.send(_devId, _data.equals("true") ? new byte[]{0x00} : new byte[]{0x01}, 0);
                 */
                System.out.println("Sending: " + response);
                client.send(_devId, response);
            } catch (Exception ex) {
                System.out.println("Response failed: " + ex.getMessage());
            }
        });

        client.start();
        testDecoder();
    }

    public static void testDecoder() throws Exception {
        byte [] key = new byte [] {0x2B, 0x7E, 0x15, 0x16, 0x28, (byte) 0xAE, (byte) 0xD2, (byte) 0xA6, (byte) 0xAB, (byte) 0xF7, 0x15, (byte) 0x88, 0x09, (byte) 0xCF, 0x4F, 0x3C};
        byte [] iv = new byte [] {0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0};

        String message = "thisisverysecret";

        byte[] encrypted = Crypto.encryptAES(message.getBytes(), key, iv);
        String encoded = Base64.getEncoder().encodeToString(encrypted);
        String decrypted = Crypto.decrypt(encoded, key, iv);

        System.out.println(decrypted);
    }
}
