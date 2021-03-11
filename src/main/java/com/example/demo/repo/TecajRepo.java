package com.example.demo.repo;


import com.example.demo.model.Tecaj;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TecajRepo extends CrudRepository<Tecaj, Long> {

    @Query(value = "select datum_primjene from valute order by datum_primjene desc limit 1", nativeQuery = true)
    LocalDate getLastDate();

    @Query(value = "select valuta from valute group by valuta", nativeQuery = true)
    List<String> dajValute();

    @Query(value = "select srednji_tecaj from valute where datum_primjene between ?1 and ?2 and valuta=?3", nativeQuery = true)
    List<String> proSjek(LocalDate odDatum , LocalDate doDatum, String valuta);

    @Query(value = "select srednji_tecaj from valute where datum_primjene between ?1 and ?2 and valuta=?3", nativeQuery = true)
    List<String> prosjekJedan(LocalDate valuta , LocalDate odDatum , String doDatum);


}