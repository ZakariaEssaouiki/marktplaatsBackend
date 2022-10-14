package com.marktplaats.controller;

import com.marktplaats.model.Gebruiker;
import com.marktplaats.service.GebruikerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gebruiker")
@CrossOrigin
public class GebruikerController {
    @Autowired
    private GebruikerService gebruikerService;

    @PostMapping("/add")
    public String add(@RequestBody Gebruiker gebruiker){
        if(!gebruiker.getEmail().equals("") && !gebruiker.getGebruikersnaam().equals("")){
            gebruikerService.saveGebruiker(gebruiker);
            return "Gebruiker is succesvol toegevoegd";
        }
        else{
            return "Voer een email en gebruikersnaam in";
        }
    }

    @GetMapping("/GetAll")
    public List<Gebruiker> GetAll(){
        return gebruikerService.GetAll();
    }

    @DeleteMapping("/Delete")
    public String Delete(@RequestBody Gebruiker gebruiker){
        if(gebruiker.getId() > 0){
            gebruikerService.deleteGebruikerById(gebruiker.getId());
            return "Gebruiker is succevol verwijderd";
        }
        else{
            return "Gebruiker is niet verwijderd";
        }
    }

    //@UpdateMapping("/Update")
}
