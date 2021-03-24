package com.example.demo.service;


import com.example.demo.model.Srednji;
import com.example.demo.model.Tecaj;
import com.example.demo.repo.TecajRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;


@Service
public class TecajService {

    Logger logger= LoggerFactory.getLogger(TecajService.class);

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

    public Object proSjek(String valuta, LocalDate odDatum, LocalDate doDatum, HttpServletResponse response) {
        List<String> vrijednost = tecajRepo.proSjek(valuta, odDatum, doDatum);



        if (doDatum.isBefore(odDatum)) {
            System.out.println("odradia je " + odDatum);
            response.setStatus(400);
            logger.error("Datum nije ispravan " +odDatum);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Datum nije ispravan "+odDatum);
        }
        if(odDatum.isBefore(LocalDate.parse("1994-05-30"))) {
            System.out.println("odradia je 1 " + odDatum);
            response.setStatus(400);
            logger.error("Nema datuma prije 1994-05-30");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nema datuma prije 1994-05-30");
        }
        if (!dajValute().contains(valuta)){
            System.out.println("odradia je 2 " + valuta);
            response.setStatus(400);
            logger.error("Valuta nije ispravna "+ valuta);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valuta nije ispravana");
        }
        else {
            System.out.println("vraca");
            return vrijednost;
        }
    }


    public Srednji prosjekJedan(String valuta, LocalDate odDatum, LocalDate doDatum) {
        List <String> srednja= tecajRepo.prosjekJedan(valuta,odDatum, doDatum);
        Double srednjaVrijednost=0.0;
        for (String s : srednja) {
            Double temp = Double.parseDouble(s.replace(",", "."));
            srednjaVrijednost = srednjaVrijednost + temp;
        }
        srednjaVrijednost=srednjaVrijednost/srednja.size();
        Srednji srednji= new Srednji(valuta,odDatum,doDatum,srednjaVrijednost);

        if (doDatum.isBefore(odDatum)) {
            System.out.println("odradia je6" + odDatum);
            logger.error("Datum nije ispravan "+odDatum);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Datum nije ispravan "+odDatum);
        }
        if(odDatum.isBefore(LocalDate.parse("1994-05-30"))) {
            System.out.println("odradia je 7" + odDatum);
            logger.error("Nema datuma prije 1994-05-30");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nema datuma prije 199-05-30");
        }

        if (!dajValute().contains(valuta)){
            System.out.println("odradia je 8 " + valuta);
            logger.error("Valuta nije ispravna "+valuta);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Valuta nije ispravana");
        }
        return srednji;
    }



}