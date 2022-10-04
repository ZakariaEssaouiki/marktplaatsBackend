package com.marktplaats.model;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Gebruiker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String email;
    private String gebruikersnaam;

    public Gebruiker(){}

    public void setId(int id){
        this.id = id;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setGebruikersnaam(String gebruikersnaam){
        this.gebruikersnaam = gebruikersnaam;
    }

    public int getId() {
        return this.id;
    }
    public String getGebruikersnaam(){
        return this.gebruikersnaam;
    }
    public String getEmail(){
        return this.email;
    }
}
