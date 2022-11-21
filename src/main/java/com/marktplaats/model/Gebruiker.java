package com.marktplaats.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Gebruikers")
public class Gebruiker {
    @Id
    private String id;
    @Column(unique = true)
    private String gebruikersnaam;
    @Column(unique = true)
    private String email;
    @Column(name = "voornaam")
    private String voornaam;
    @Column(name = "achternaam")
    private String achternaam;
    @Column(name = "geboorteDatum")
    private LocalDate geboorteDatum;
    @Column(name = "geslacht")
    @Enumerated(EnumType.STRING)
    private Geslacht geslacht;

/*    @OneToMany(targetEntity = Product.class)
    private List<Product> producten;*/

    public Gebruiker(String gebruikersnaam, String email, String voornaam,String achternaam,
                     LocalDate geboorteDatum, Geslacht geslacht,String id) {
        this.gebruikersnaam = gebruikersnaam;
        this.email = email;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboorteDatum = geboorteDatum;
        this.geslacht = geslacht;
        this.id = id;
    }
    public Gebruiker(){}
    public String getId() {return id;}
    public void setId(String id) {this.id = id;}
    public String getGebruikersnaam() {return gebruikersnaam;}
    public void setGebruikersnaam(String gebruikersnaam) {this.gebruikersnaam = gebruikersnaam;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public LocalDate getGeboorteDatum() {return geboorteDatum;}
    public void setGeboorteDatum(LocalDate geboorteDatum) {this.geboorteDatum = geboorteDatum;}
    public Geslacht getGeslacht() {return geslacht;}
    public void setGeslacht(Geslacht geslacht) {this.geslacht = geslacht;}
    public String getVoornaam() {return voornaam;}
    public void setVoornaam(String voornaam) {this.voornaam = voornaam;}
    public String getAchternaam() {return achternaam;}
    public void setAchternaam(String achternaam) {this.achternaam = achternaam;}
/*    public void setProducten(List<Product> producten){this.producten = producten;}
    public List<Product> getProducten(){return this.producten;}*/

    @Override
    public String toString() {
        return this.gebruikersnaam;
    }
}
