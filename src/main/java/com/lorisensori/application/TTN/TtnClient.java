package com.lorisensori.application.TTN;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.thethingsnetwork.data.mqtt.Client;
import java.net.URISyntaxException;


@Component
public class TtnClient {

    @Bean
    public Client getInstanceOfClient() throws URISyntaxException {
        String REGION = "eu";
        String APP_ID = "tanks_lorisensori";
        String ACCESS_KEY = "ttn-account-v2.S4DKj7oir_lt9lLyXg_3yZU-UDdVkzlDgZfnoIFzbec";
        return new Client(REGION, APP_ID, ACCESS_KEY);
    }
}




