package com.marktplaats.controller;

import com.google.gson.Gson;
import com.marktplaats.model.Gebruiker;
import com.marktplaats.model.GebruikerProducten;
import com.marktplaats.model.Product;
import com.marktplaats.service.GebruikerService;
import com.marktplaats.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/gebruiker")
@CrossOrigin
public class GebruikerController {
    @Autowired
    private GebruikerService gebruikerService;

    @Autowired
    private ProductService productService;

    final private Gson gson = new Gson();

    public GebruikerController(GebruikerService gebruikerService, ProductService productService){

        this.gebruikerService = gebruikerService;
        this.productService = productService;
    }

    @RequestMapping(value = "/producten/{id}",method = RequestMethod.GET)
    public ResponseEntity<Object> GetAllProducten(@PathVariable int id){
        List<GebruikerProducten> gebruikerProducten = gebruikerService.GetAllProducten(id);
        List<Product>products = new ArrayList<>();
        for (var element:gebruikerProducten) {
            products.add(element.getProduct());
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<Object> GetAll(){
        List<Gebruiker> gebruikers = gebruikerService.GetAll();
        return new ResponseEntity<>(gebruikers,HttpStatus.OK);
    }

    @RequestMapping(value = "/voegProductToe", method = RequestMethod.POST)
    public ResponseEntity<Object> VoegProductToe(@RequestBody GebruikerProducten gebruikerProducten){
        if(Objects.nonNull(gebruikerProducten.gebruiker) || Objects.nonNull(gebruikerProducten.product)){
            this.gebruikerService.VoegProductToe(gebruikerProducten);
            return new ResponseEntity<>(gson.toJson("Product is succesvol toegevoegd."),HttpStatus.CREATED);
        }
        return new ResponseEntity<>(gson.toJson("Er is een fout ontstaan met het toevoeven van de product."),HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/deleteProduct/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Object> VerwijderProduct(@PathVariable int id, @RequestBody Product product){
        Optional<Gebruiker> gebruiker = gebruikerService.FindById(id);
        if(product != null && !gebruiker.isEmpty()) {
            this.gebruikerService.VerwijderProduct(id, product);
            this.productService.Delete(product);
            return new ResponseEntity<>(gson.toJson("Product is succesvol verwijderd."), HttpStatus.OK);
        }
        return new ResponseEntity<>(gson.toJson("Er is iets misgegaan met het verwijderen van het product."),HttpStatus.BAD_REQUEST);
    }
}
