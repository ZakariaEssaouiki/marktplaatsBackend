package com.marktplaats.controller;

import com.google.gson.Gson;
import com.marktplaats.model.Gebruiker;
import com.marktplaats.model.Product;
import com.marktplaats.service.GebruikerService;
import com.marktplaats.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@CrossOrigin
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

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public ResponseEntity<Object> GetAll() {
        return new ResponseEntity<>(productService.GetAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Object> VoegProductToe(@RequestBody Product product){
        if(product != null){
            this.productService.Create(product);
            return new ResponseEntity<>(gson.toJson("Product succesvol aangemaakt"),HttpStatus.CREATED);
        }
        return new ResponseEntity<>(gson.toJson("Product kon niet worden toegevoegd"),HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/findUserByProduct/{id}")
    public ResponseEntity<Object> FindGebruikerVanProduct(@PathVariable int id){
        if(!this.productService.FindById(id).isEmpty()){
            Gebruiker gebruiker = this.gebruikerService.FindByProductId(id);
            return new ResponseEntity<>(gebruiker,HttpStatus.OK);
        }
        return new ResponseEntity<>(gson.toJson("Er is iets misgegaan."),HttpStatus.BAD_REQUEST);
    }

}
