package com.example.demo.service;


import com.example.demo.model.Tecaj;
import com.example.demo.repo.TecajRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;


@Service
public class TecajService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    private TecajRepo tecajRepo;


    public void upadateajBazu(LocalDate start) {
        LocalDate pocetak = start;
        LocalDate danas = LocalDate.now();
        boolean zadnji = false;
        while (!zadnji) {
            if (!pocetak.isEqual(danas.plusDays(1))) {
                Tecaj[] sviTecajevi = webClientBuilder.build()
                        .get()
                        .uri("http://api.hnb.hr/tecajn/v2?datum-primjene=" + pocetak.toString())
                        .retrieve()
                        .bodyToMono(Tecaj[].class)
                        .block();
                for (int i = 0; i < sviTecajevi.length; i++) {
                    Tecaj var = sviTecajevi[i];
                    tecajRepo.save(var);
                }
                pocetak = pocetak.plusDays(1);
            } else {
                zadnji = true;
            }
        }
    }


    public void napuniBazu() {
        LocalDate pocetak = LocalDate.parse("1994-05-30");
        LocalDate danas = LocalDate.now();
        System.out.println(pocetak.toString());
        boolean zadnji = false;
        while (!zadnji) {
            if (!pocetak.isEqual(danas.plusDays(0))) {
                Tecaj[] sviTecajevi = webClientBuilder.build()
                        .get()
                        .uri("http://api.hnb.hr/tecajn/v2?datum-primjene=" + pocetak.toString())
                        .retrieve()
                        .bodyToMono(Tecaj[].class)
                        .block();
                for (int i = 0; i < sviTecajevi.length; i++) {
                    Tecaj var = sviTecajevi[i];
                    tecajRepo.save(var);
                }
                pocetak = pocetak.plusDays(1);
            } else {
                zadnji = true;
            }
        }
    }


    public List<String> dajValute() {
        return tecajRepo.dajValute();
    }

    public List<String> proSjek(LocalDate odDatum, LocalDate doDatum, String valuta) {
        return  tecajRepo.proSjek(odDatum, doDatum,valuta);
    }
    public Double prosjekJedan(LocalDate odDatum, LocalDate doDatum, String valuta) {
        List <String> srednja= tecajRepo.prosjekJedan(odDatum, doDatum,valuta);
        Double srednjaVrijednost=0.0;
        for (String s : srednja) {
            Double temp = Double.parseDouble(s.replace(",", "."));
            srednjaVrijednost = srednjaVrijednost + temp;
        }
        srednjaVrijednost=srednjaVrijednost/srednja.size();
        return srednjaVrijednost;
    }

}