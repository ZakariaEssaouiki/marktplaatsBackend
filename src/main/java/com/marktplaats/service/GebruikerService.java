package com.marktplaats.service;

import com.marktplaats.model.Gebruiker;
import com.marktplaats.model.GebruikerProducten;
import com.marktplaats.model.Product;
import com.marktplaats.repository.GebruikerProductenRepo;
import com.marktplaats.repository.GebruikerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GebruikerService implements IGebruikerService{
    @Autowired
    private GebruikerRepository repo;

    @Autowired
    private GebruikerProductenRepo gebruikerProductenRepo;

    public GebruikerService(GebruikerRepository repo, GebruikerProductenRepo gebruikerProductenRepo){
        this.repo = repo;
        this.gebruikerProductenRepo = gebruikerProductenRepo;
    }
    @Override
    public Optional<Gebruiker> FindById(int id) {
        Optional<Gebruiker> gebruiker = repo.findById(id);
        return gebruiker;
    }

    @Override
    public void Create(Gebruiker gebruiker) {
        repo.save(gebruiker);
    }

    @Override
    public void Delete(Gebruiker gebruiker) {
        repo.delete(gebruiker);
    }

    @Override
    public boolean GebruikersnaamAlInGebruik(String gebruikersnaam) {
        if(!gebruikersnaam.isEmpty() && !gebruikersnaam.isBlank()){
            if(!repo.findGebruikerByGebruikersnaam(gebruikersnaam)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean EmailAlInGebruik(String email) {
        if(!email.isEmpty() && !email.isBlank()){
            if(!repo.findGebruikerByEmail(email)){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Gebruiker> GetAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Gebruiker> Login(String gebrOfEmail, String wachtwoord) {
        Optional<Gebruiker> gebruiker = repo.findGebruikerByGebruikersnaamAndWachtwoordOrEmailAndWachtwoord(gebrOfEmail, wachtwoord,gebrOfEmail,wachtwoord);
        return gebruiker;
    }

    @Override
    public List<GebruikerProducten> GetAllProducten(int id) {
        return this.gebruikerProductenRepo.findAllByGebruiker_Id(id);
    }

    @Override
    public void VoegProductToe(GebruikerProducten gebruikerProducten) {
        gebruikerProductenRepo.save(gebruikerProducten);
    }

    @Override
    public void VerwijderProduct(int id, Product product) {
        this.gebruikerProductenRepo.removeGebruikerProductenByGebruiker_IdAndProduct_Id(id,product.getId());
    }
}
