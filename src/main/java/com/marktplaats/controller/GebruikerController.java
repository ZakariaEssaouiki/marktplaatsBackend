package com.marktplaats.controller;

import com.marktplaats.model.Product;
import com.marktplaats.service.GebruikerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gebruiker")
@CrossOrigin
public class GebruikerController {
    @Autowired
    private GebruikerService gebruikerService;

    public GebruikerController(GebruikerService gebruikerService){
        this.gebruikerService = gebruikerService;
    }

    @GetMapping("/producten")
    public ResponseEntity<Object> GetAllProducten(int id){
        List<Product> producten = gebruikerService.GetAllProducten(id);
        return new ResponseEntity<>(producten, HttpStatus.OK);
    }

    //getAll method implementeren.

}
