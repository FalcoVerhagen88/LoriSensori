package com.lorisensori.application;


import java.net.URISyntaxException;

import com.lorisensori.application.TTN.DownlinkHandler;
import com.lorisensori.application.domain.Tank;
import com.lorisensori.application.service.TankService;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.modelmapper.ModelMapper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thethingsnetwork.data.common.messages.DataMessage;
import org.thethingsnetwork.data.mqtt.Client;

import com.lorisensori.application.DAO_interfaces.TankRepository;
import com.lorisensori.application.TTN.TtnUplinkHandler;

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


@SpringBootApplication
@EntityScan(basePackageClasses = {
        Application.class,
        Jsr310JpaConverters.class //used for converting JDK8 Dates to spring boot standards
})


public class Application {

    private static final String REGION = "eu";
    private static final String APP_ID = "tanks_lorisensori";
    private static final String ACCESS_KEY = "ttn-account-v2.S4DKj7oir_lt9lLyXg_3yZU-UDdVkzlDgZfnoIFzbec";
    private static byte[] payload;
    private static TankService tserv;
    private static Client CLIENT;
    private static DownlinkHandler downlinkHandler = new DownlinkHandler();


    static {
        try {
            CLIENT = new Client(REGION, APP_ID, ACCESS_KEY);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
        try {
            System.out.println(tserv.findByTankId((long) 1).getTanknaam());
        } catch (NullPointerException exc) {
            System.out.println("doet t niet");
        }
//        DownlinkMessage response = new DownlinkMessage(1, downlinkHandler.setDieselNiveau(50));
//        System.out.println("Sending: " + response);

        CLIENT.onMessage((String devId, DataMessage data) -> {

//            System.out.println("/n kom ik tot hier?");
            try {
//                Thread uplink = new Thread(new TtnUplinkHandler(CLIENT, data, devId));
//                uplink.start();
//                DownlinkMessage response = new DownlinkMessage(1, downlinkHandler.setSluitingsTijd(17, 0));
//                System.out.println("Sending: " + response);
//
//                CLIENT.send(devId, response);

            } catch (Exception ex) {
                System.out.println("Response failed: " + ex.getMessage());
            }
        });

        try {
            CLIENT.start();
        } catch (MqttException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Client client() throws URISyntaxException {
        return new Client(REGION, APP_ID, ACCESS_KEY);
    }

}
