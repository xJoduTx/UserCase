package com.apiVerse.UserCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class UserCaseApplication {

    @Autowired
    private Ulkaas ulkaas;

    public static void main(String[] args) {
        SpringApplication.run(UserCaseApplication.class, args);
    }

//    @Scheduled(fixedRateString = "2000")
//    public void Ulmasssss() {
//        for (int i = 0; i < 10; i++) {
//            ulkaas.sendMEssage(String.valueOf(i));
//        }
//    }
}

