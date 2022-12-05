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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private GebruikerService gebruikerService;
    final private Gson gson = new Gson();
    public ProductController(ProductService productService,GebruikerService gebruikerService){
        this.productService = productService;
        this.gebruikerService = gebruikerService;
    }

    /**Methode die alle producten ophaalt.*/
    @GetMapping(value = "/getAll")
    public ResponseEntity<Object> GetAll() {
        return new ResponseEntity<>(productService.GetAll(), HttpStatus.OK);
    }

    /**Methode die een product aanmaakt.*/
    @PostMapping(value = "/add")
    public ResponseEntity<Object> VoegProductToe(@RequestBody Product product,@AuthenticationPrincipal OAuth2User principal){
        String id = principal.getAttributes().get("sub").toString();
        Gebruiker gebruiker = this.gebruikerService.FindById(id);
        if(gebruiker != null && product != null){
            this.productService.Create(product);
            GebruikerProducten gebruikerProducten = new GebruikerProducten();
            gebruikerProducten.setProduct(product);
            gebruikerProducten.setGebruiker(gebruiker);
            this.gebruikerService.VoegProductToe(gebruikerProducten);
            return new ResponseEntity<>(gson.toJson("Product succesvol aangemaakt"),HttpStatus.CREATED);
        }
        return new ResponseEntity<>(gson.toJson("Product kon niet worden toegevoegd"),HttpStatus.BAD_REQUEST);
    }

    /**Methode die een een gebruiker teruggeeft op basis van de meegegegevn product id.*/
    @GetMapping(value = "/findUserByProduct/{id}")
    public ResponseEntity<Object> FindGebruikerVanProduct(@PathVariable int id){
        if(this.productService.FindById(id) != null){
            Gebruiker gebruiker = this.gebruikerService.FindByProductId(id);
            return new ResponseEntity<>(gebruiker,HttpStatus.OK);
        }
        return new ResponseEntity<>(gson.toJson("Er is iets misgegaan."),HttpStatus.BAD_REQUEST);
    }

}
