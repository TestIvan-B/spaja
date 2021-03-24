package com.example.demo.controller;


import com.example.demo.model.Srednji;
import com.example.demo.service.TecajService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
public class TecajController {

    Logger logger = LoggerFactory.getLogger(TecajController.class);

    @Autowired
    TecajService tecajService;



    @GetMapping("/tecajevi")
    public void getValute() {
        tecajService.napuniBazu();
    }


    @GetMapping(value = "/valute", produces = "application/json")
    public List<String> daValute() {

        return this.tecajService.dajValute();
    }

    @GetMapping(path = "/prosjek", produces = "application/json")
    public Object getProsjek(@RequestParam(required = false) String valuta,
                             @RequestParam (required = false) String odDatum,
                             @RequestParam (required = false) String doDatum,
                             HttpServletResponse response,
                             HttpServletRequest request) {
        return tecajService.proSjek(valuta.toUpperCase(), LocalDate.parse(odDatum), LocalDate.parse(doDatum),response);
    }

    @GetMapping(path = "/proSrednja",produces = "application/json")
    public Srednji getProsjekSrednja(@RequestParam(required = false) String valuta,
                                     @RequestParam (required = false) String odDatum,
                                     @RequestParam (required = false) String doDatum) {
        return tecajService.prosjekJedan(valuta.toUpperCase(), LocalDate.parse(odDatum), LocalDate.parse(doDatum));

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullPointerException.class)
    public String handleExceptions(NullPointerException exception) {

        String message = exception.getMessage();
        logger.error("Vrijednost nije unesena");

        return message;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DateTimeParseException.class)
    public String handleExceptions(DateTimeParseException e) {

        String message = e.getMessage();
        logger.error("Krivo unesen datum");
        return  message;
    }


}




