package com.example.demo.controller;


import com.example.demo.service.TecajService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TecajController {

    @Autowired
    TecajService tecajService;


    @GetMapping("/tecajevi")
    public void getValute() {
        tecajService.napuniBazu();
    }

    @GetMapping("/valute")
    public List<String> daValute() {
   /*     List<String> lista = tecajService.dajValute();
        for (String s : lista){
            System.out.println(s);
        }*/
        return tecajService.dajValute();
    }

    @GetMapping("/prosjek/{valute}/{odDatum}/{doDatum}")
    public List<String> getProsjek(@PathVariable(name = "odDatum") String odDatum
            , @PathVariable(name = "doDatum") String doDatum
            , @PathVariable(name = "valute") String valuta) {
        return tecajService.proSjek(LocalDate.parse(odDatum), LocalDate.parse(doDatum), valuta);
    }

    @GetMapping(path = "/proSrednja/{valute}/{odDatum}/{doDatum}")
    public Double getProsjekSrednja(@PathVariable(name = "odDatum") String odDatum
            , @PathVariable(name = "doDatum") String doDatum
            , @PathVariable(name = "valute") String valuta) {
        return tecajService.prosjekJedan(LocalDate.parse(odDatum), LocalDate.parse(doDatum), valuta);
    }
}


