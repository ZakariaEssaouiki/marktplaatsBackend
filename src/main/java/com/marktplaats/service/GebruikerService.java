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
    public Gebruiker FindById(String id) {
        Gebruiker gebruiker = repo.findGebruikerById(id);
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
    public void Update(Gebruiker gebruiker){
        Gebruiker user = this.repo.findGebruikerById(gebruiker.getId());
        user.setEmail(gebruiker.getEmail());
        user.setId(gebruiker.getId());
        user.setGebruikersnaam(gebruiker.getGebruikersnaam());
        user.setVoornaam(gebruiker.getVoornaam());
        user.setAchternaam(gebruiker.getAchternaam());
        user.setGeslacht(gebruiker.getGeslacht());
        user.setGeboorteDatum(gebruiker.getGeboorteDatum());
        this.repo.save(user);
    }

    @Override
    public boolean GebruikersnaamAlInGebruik(String gebruikersnaam) {
        if(!gebruikersnaam.isEmpty() && !gebruikersnaam.isBlank()){
            if(repo.existsGebruikerByGebruikersnaam(gebruikersnaam)){
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
    public List<GebruikerProducten> GetAllProducten(String id) {
        return this.gebruikerProductenRepo.findAllByGebruiker_Id(id);
    }

    @Override
    public void VoegProductToe(GebruikerProducten gebruikerProducten) {
        gebruikerProductenRepo.save(gebruikerProducten);
    }

    @Override
    public void VerwijderProduct(String id, Product product) {
        this.gebruikerProductenRepo.removeGebruikerProductenByGebruiker_IdAndProduct_Id(id,product.getId());
    }

    @Override
    public void VerwijderAlleProducten(String id) {
        this.gebruikerProductenRepo.deleteAllByGebruiker_Id(id);
    }

    @Override
    public Gebruiker FindByProductId(int id) {
        Gebruiker gebruiker = this.gebruikerProductenRepo.findGebruikerProductenByProduct_Id(id).getGebruiker();
        return gebruiker;
    }

    @Override
    public Gebruiker FindByGebruikersnaam(String gebruikersnaam) {
        return this.repo.findGebruikerByGebruikersnaam(gebruikersnaam);
    }
}
