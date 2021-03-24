package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Srednji {


    Double srednjaVrijednost;


    public Srednji(String valuta, LocalDate odDatum, LocalDate doDatum, Double srednjaVrijednost) {
        this.srednjaVrijednost = srednjaVrijednost;
    }
}
