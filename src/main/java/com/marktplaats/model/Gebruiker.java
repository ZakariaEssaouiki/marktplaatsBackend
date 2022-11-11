package com.marktplaats.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "Gebruikers")
public class Gebruiker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String gebruikersnaam;
    @Column(unique = true)
    private String email;
    private String wachtwoord;
    @Column(name = "voornaam")
    private String voornaam;
    @Column(name = "achternaam")
    private String achternaam;
    @Column(name = "geboorteDatum")
    private LocalDate geboorteDatum;
    @Column(name = "geslacht")
    @Enumerated(EnumType.STRING)
    private Geslacht geslacht;

    //@OneToMany(targetEntity = Product.class)
    //private List<Product> producten;

    public Gebruiker(String gebruikersnaam, String email, String wachtwoord, String voornaam,String achternaam,
                     LocalDate geboorteDatum, Geslacht geslacht,int id) {
        this.gebruikersnaam = gebruikersnaam;
        this.email = email;
        this.wachtwoord = wachtwoord;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboorteDatum = geboorteDatum;
        this.geslacht = geslacht;
        this.id = id;
    }

    public Gebruiker(Optional<Gebruiker> gebruiker){
        if(gebruiker.isPresent()){
            this.gebruikersnaam = gebruiker.get().getGebruikersnaam();
            this.email = gebruiker.get().getEmail();
            this.wachtwoord = gebruiker.get().getWachtwoord();
            this.voornaam = gebruiker.get().getVoornaam();
            this.achternaam = gebruiker.get().getAchternaam();
            this.geboorteDatum = gebruiker.get().getGeboorteDatum();
            this.geslacht = gebruiker.get().getGeslacht();
            this.id = gebruiker.get().getId();
        }
    }
    public Gebruiker(){}

    public int getLeeftijd(){
        return (int)ChronoUnit.YEARS.between(this.geboorteDatum, LocalDate.now());
    }

    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getGebruikersnaam() {return gebruikersnaam;}
    public void setGebruikersnaam(String gebruikersnaam) {this.gebruikersnaam = gebruikersnaam;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getWachtwoord() {return wachtwoord;}
    public void setWachtwoord(String wachtwoord) {this.wachtwoord = wachtwoord;}
    public LocalDate getGeboorteDatum() {return geboorteDatum;}
    public void setGeboorteDatum(LocalDate geboorteDatum) {this.geboorteDatum = geboorteDatum;}
    public Geslacht getGeslacht() {return geslacht;}
    public void setGeslacht(Geslacht geslacht) {this.geslacht = geslacht;}
    public String getVoornaam() {return voornaam;}
    public void setVoornaam(String voornaam) {this.voornaam = voornaam;}
    public String getAchternaam() {return achternaam;}
    public void setAchternaam(String achternaam) {this.achternaam = achternaam;}
    //public void setProducten(List<Product> producten){this.producten = producten;}
    //public List<Product> getProducten(){return this.producten;}
    @Override
    public String toString() {
        return this.gebruikersnaam;
    }
}
