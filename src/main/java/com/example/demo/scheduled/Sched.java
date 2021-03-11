package com.example.demo.scheduled;


import com.example.demo.repo.TecajRepo;
import com.example.demo.service.TecajService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;

@Configuration
@EnableScheduling
public class Sched {

    @Autowired
    TecajService tecajService;

    @Autowired
    TecajRepo tecajRepo;

    @Scheduled(fixedDelay = 1000000)
    public void schedDelay() {

        LocalDate localDate = LocalDate.now();
        LocalDate lastDate = tecajRepo.getLastDate();
        System.out.println(localDate);
        System.out.println(lastDate);

        if (lastDate == null) {
            System.out.println("Punjenje");
            tecajService.napuniBazu();
            System.out.println("Napunjeno");
        }
        else if (lastDate.equals(localDate)) {
            System.out.println("Vec je updateano");
        }
        else {
            System.out.println("Updatanje");
            tecajService.upadateajBazu(lastDate);
            System.out.println("Updatanje gotovo");
        }
    }
}
