package com.example.demo.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@Entity
@Table(name = "valute")
public class Tecaj{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "broj_tecajnice")
    String broj_tecajnice;

    @Column(name = "datum_primjene")
    LocalDate datum_primjene;

    @Column(name = "drzava")
    String drzava;

    @Column(name = "drzava_iso")
    String drzava_iso;

    @Column(name = "valuta")
    String valuta;

    @Column(name = "sifra_valute")
    String sifra_valute;

    @Column(name = "jedinica")
    int jedinica;

    @Column(name = "kupovni_tecaj")
    String kupovni_tecaj;

    @Column(name = "srednji_tecaj")
    String srednji_tecaj;

    @Column(name = "prodajni_tecaj")
    String prodajni_tecaj;

    public Tecaj(String broj_tecajnice, LocalDate datum_primjene, String drzava, String drzava_iso, String valuta, String sifra_valute, int jedinica, String kupovni_tecaj, String srednji_tecaj, String prodajni_tecaj) {
        this.broj_tecajnice = broj_tecajnice;
        this.datum_primjene = datum_primjene;
        this.drzava = drzava;
        this.drzava_iso = drzava_iso;
        this.valuta = valuta;
        this.sifra_valute = sifra_valute;
        this.jedinica = jedinica;
        this.kupovni_tecaj = kupovni_tecaj;
        this.srednji_tecaj = srednji_tecaj;
        this.prodajni_tecaj = prodajni_tecaj;
    }




    public Tecaj() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBroj_tecajnice() {
        return broj_tecajnice;
    }

    public void setBroj_tecajnice(String broj_tecajnice) {
        this.broj_tecajnice = broj_tecajnice;
    }

    public LocalDate getDatum_primjene() {
        return datum_primjene;
    }

    public void setDatum_primjene(LocalDate datum_primjene) {
        this.datum_primjene = datum_primjene;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getDrzava_iso() {
        return drzava_iso;
    }

    public void setDrzava_iso(String drzava_iso) {
        this.drzava_iso = drzava_iso;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public String getSifra_valute() {
        return sifra_valute;
    }

    public void setSifra_valute(String sifra_valute) {
        this.sifra_valute = sifra_valute;
    }

    public int getJedinica() {
        return jedinica;
    }

    public void setJedinica(int jedinica) {
        this.jedinica = jedinica;
    }

    public String getKupovni_tecaj() {
        return kupovni_tecaj;
    }

    public void setKupovni_tecaj(String kupovni_tecaj) {
        this.kupovni_tecaj = kupovni_tecaj;
    }

    public String getSrednji_tecaj() {
        return srednji_tecaj;
    }

    public void setSrednji_tecaj(String srednji_tecaj) {
        this.srednji_tecaj = srednji_tecaj;
    }

    public String getProdajni_tecaj() {
        return prodajni_tecaj;
    }

    public void setProdajni_tecaj(String prodajni_tecaj) {
        this.prodajni_tecaj = prodajni_tecaj;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tecaj{");
        sb.append("id=").append(id);
        sb.append(", broj_tecajnice='").append(broj_tecajnice).append('\'');
        sb.append(", datum_primjene=").append(datum_primjene);
        sb.append(", drzava='").append(drzava).append('\'');
        sb.append(", drzava_iso='").append(drzava_iso).append('\'');
        sb.append(", valuta='").append(valuta).append('\'');
        sb.append(", sifra_valute='").append(sifra_valute).append('\'');
        sb.append(", jedinica=").append(jedinica);
        sb.append(", kupovni_tecaj='").append(kupovni_tecaj).append('\'');
        sb.append(", srednji_tecaj='").append(srednji_tecaj).append('\'');
        sb.append(", prodajni_tecaj='").append(prodajni_tecaj).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

