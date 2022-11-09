package com.marktplaats.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Producten")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String naam;
    private double prijs;
    private LocalDate datumGeplaatst;
    private String beschrijving;

    public Product(String naam, double prijs, String beschrijving, LocalDate datumGeplaatst, int id){
        this.naam = naam;
        this.prijs = prijs;
        this.datumGeplaatst = datumGeplaatst;
        this.beschrijving = beschrijving;
        this.id = id;
    }

    public Product(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public LocalDate getDatumGeplaatst() {
        return datumGeplaatst;
    }

    public void setDatumGeplaatst(LocalDate datumGeplaatst) {
        this.datumGeplaatst = datumGeplaatst;
    }

    public String getBeschrijving() {
        return this.beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    @Override
    public String toString() {
        return "Product: " + this.naam + " prijs: " + this.prijs
                + " datum geplaatst: " + this.datumGeplaatst + " beschrijving: " + this.beschrijving;
    }
}
