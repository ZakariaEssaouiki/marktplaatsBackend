package com.marktplaats.service;

import com.marktplaats.model.Gebruiker;
import com.marktplaats.model.Product;

import java.util.List;
import java.util.Optional;

public interface IGebruikerService {

    public Optional<Gebruiker> FindById(int id);
    public void Create(Gebruiker gebruiker);
    public void Delete(Gebruiker gebruiker);
    public boolean GebruikersnaamAlInGebruik(String gebruikersnaam);
    public boolean EmailAlInGebruik(String email);
    public List<Gebruiker> GetAll();
    public Optional<Gebruiker> Login(String gebrOfEmail, String wachtwoord);
    public List<Product> GetAllProducten(int id);
    public void VoegProductToe(Gebruiker gebruiker, Product product);
    public void VerwijderProduct(int id, Product product);
}
