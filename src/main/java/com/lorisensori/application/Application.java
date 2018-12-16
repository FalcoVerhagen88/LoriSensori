package com.lorisensori.application;

import com.lorisensori.application.domain.Bedrijf;
import com.lorisensori.application.domain.Tank;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        Bedrijf bedrijf = new Bedrijf();
        Tank tank = new Tank();

        bedrijf.addTank(tank);
        bedrijf.addTank(tank);
        bedrijf.addTank(tank);
        bedrijf.addTank(tank);


    }

}
