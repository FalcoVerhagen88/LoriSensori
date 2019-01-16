package com.lorisensori.application;


import java.net.URISyntaxException;

import com.lorisensori.application.TTN.DownlinkHandler;
import com.lorisensori.application.service.SensorLogService;
import com.lorisensori.application.service.SensorgegevensService;
import com.lorisensori.application.service.TankService;

import com.lorisensori.application.service.TankServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.thethingsnetwork.data.common.messages.DataMessage;
import org.thethingsnetwork.data.mqtt.Client;


import com.lorisensori.application.TTN.TtnUplinkHandler;

import java.util.concurrent.Executor;


@SpringBootApplication
@EnableAsync
@ComponentScan("com.lorisensori.application.service")
@ComponentScan("com.lorisensori.application.TTN")
@EntityScan(basePackageClasses = {
        Application.class,
        Jsr310JpaConverters.class, //used for converting JDK8 Dates to spring boot standards
        TtnUplinkHandler.class


})


public class Application {

    private static final String REGION = "eu";
    private static final String APP_ID = "tanks_lorisensori";
    private static final String ACCESS_KEY = "ttn-account-v2.S4DKj7oir_lt9lLyXg_3yZU-UDdVkzlDgZfnoIFzbec";



    private static Client CLIENT;


    static {
        try {
            CLIENT = new Client(REGION, APP_ID, ACCESS_KEY);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
        ApplicationContext context = SpringApplication.run(Application.class, args);
        TankService tankService = (TankService) context.getBean("tankService");
        SensorgegevensService sensorservice = (SensorgegevensService) context.getBean("SensorgegevensService");
        SensorLogService sensorlog = (SensorLogService) context.getBean("SensorLogService");
        TtnUplinkHandler handler = (TtnUplinkHandler) context.getBean("UplinkHandler");

        CLIENT.onMessage((String devId, DataMessage data) -> {
            try {
                System.out.println("haha");
                
              
                handler.setdevId(devId);
                handler.setUplinkMessage(data);
                handler.setClient(CLIENT);
                handler.setTankService(tankService);
                handler.setSensorgegevensService(sensorservice);
                handler.setSensorLogService(sensorlog);
                Thread receiveUplink = new Thread(handler);
                receiveUplink.start();
            }
            catch (NullPointerException enull) {
                enull.printStackTrace();
            }

            catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Response failed: " + ex.getMessage());
            }
        });

        try {
            CLIENT.start();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            // e.printStackTrace();
        }



    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public Executor asyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("JDAsync-");
        executor.initialize();
        return executor;
    }
    @Bean
    public Client client() throws URISyntaxException {
        return new Client(REGION, APP_ID, ACCESS_KEY);
    }

}
