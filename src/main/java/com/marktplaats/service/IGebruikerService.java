package com.marktplaats.service;

import com.marktplaats.model.Gebruiker;
import com.marktplaats.model.GebruikerProducten;
import com.marktplaats.model.Product;

import java.util.List;
import java.util.Optional;

public interface IGebruikerService {

    public Gebruiker FindById(String id);
    public void Create(Gebruiker gebruiker);
    public void Delete(Gebruiker gebruiker);
    public void Update(Gebruiker gebruiker);
    public boolean GebruikersnaamAlInGebruik(String gebruikersnaam);
    public boolean EmailAlInGebruik(String email);
    public List<Gebruiker> GetAll();
    public List<GebruikerProducten> GetAllProducten(String id);
    public void VoegProductToe(GebruikerProducten gebruikerProducten);
    public void VerwijderProduct(String id, Product product);
    public void VerwijderAlleProducten(String id);
    public Gebruiker FindByProductId(int id);
    public Gebruiker FindByGebruikersnaam(String gebruikersnaam);
}
