package com.marktplaats.service;

import com.marktplaats.model.Gebruiker;
import com.marktplaats.repository.GebruikerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GebruikerServiceImplentatie implements GebruikerService{

    @Autowired
    private GebruikerRepository gebruikerRepository;
    @Override
    public Gebruiker saveGebruiker(Gebruiker gebruiker) {
        return gebruikerRepository.save(gebruiker);
    }

    @Override
    public List<Gebruiker> GetAll() {
        return gebruikerRepository.findAll();
    }

    @Override
    public void deleteGebruikerById(int id){gebruikerRepository.deleteById(id);}
}
