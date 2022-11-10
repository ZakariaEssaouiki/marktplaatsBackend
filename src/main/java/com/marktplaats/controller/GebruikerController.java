package com.marktplaats.controller;

import com.google.gson.Gson;
import com.marktplaats.model.Gebruiker;
import com.marktplaats.model.Product;
import com.marktplaats.service.GebruikerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/gebruiker")
@CrossOrigin
public class GebruikerController {
    @Autowired
    private GebruikerService gebruikerService;

    final private Gson gson = new Gson();

    public GebruikerController(GebruikerService gebruikerService){
        this.gebruikerService = gebruikerService;
    }

    @GetMapping("/producten/{id}")
    public ResponseEntity<Object> GetAllProducten(@PathVariable("id") int id){
        List<Product> producten = gebruikerService.GetAllProducten(id);
        return new ResponseEntity<>(producten, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<Object> GetAll(){
        List<Gebruiker> gebruikers = gebruikerService.GetAll();
        return new ResponseEntity<>(gebruikers,HttpStatus.OK);
    }

    @PutMapping("/voegProductToe")
    public ResponseEntity<Object> VoegProductToe(@RequestBody Gebruiker gebruiker, @RequestBody Product product){
        if(Objects.nonNull(gebruiker) && Objects.nonNull(product)){
            this.gebruikerService.VoegProductToe(gebruiker,product);
            return new ResponseEntity<>(gson.toJson("Product is succesvol toegevoegd."),HttpStatus.CREATED);
        }
        return new ResponseEntity<>(gson.toJson("Er is een fout ontstaan met het toevoeven van de product."),HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<Object> VerwijderProduct(@PathVariable("id") int id, @RequestBody Product product){
        if(Objects.nonNull(product) && Objects.nonNull(gebruikerService.FindById(id))) {
            this.gebruikerService.VerwijderProduct(id, product);
            return new ResponseEntity<>(gson.toJson("Product is succesvol verwijderd."), HttpStatus.OK);
        }
        return new ResponseEntity<>(gson.toJson("Er is iets misgegaan met het verwijderen van het product."),HttpStatus.BAD_REQUEST);
    }
}
