package com.marktplaats.service;

import com.marktplaats.model.Gebruiker;

import java.util.List;

public interface GebruikerService {
    public Gebruiker saveGebruiker(Gebruiker gebruiker);
    public List<Gebruiker> GetAll();
    public void deleteGebruikerById(int id);
}
