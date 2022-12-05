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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/gebruiker")
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

    /**Methode om de gebruiker te updaten.*/
    @PutMapping(value = "/update")
    public ResponseEntity<Object> UpdateGebruiker(@RequestBody Gebruiker gebruiker,@AuthenticationPrincipal OAuth2User user){
        String id = user.getAttributes().get("sub").toString();
        String email = user.getAttributes().get("email").toString();
        String gebruikersnaam = user.getAttributes().get("name").toString();
        if(this.gebruikerService.FindById(id) != null && AllFieldsAreFilled(gebruiker)){
            gebruiker.setEmail(email);
            gebruiker.setId(id);
            gebruiker.setGebruikersnaam(gebruikersnaam);
            this.gebruikerService.Update(gebruiker);
            return new ResponseEntity<>(gson.toJson("Gebruiker is succesvol geupdatet."),HttpStatus.OK);
        }
        return new ResponseEntity<>(gson.toJson("Er ging iets mis."),HttpStatus.BAD_REQUEST);
    }

    /**controleert of alle attributen van Gebruiker zijn ingevoerd.*/
    public boolean AllFieldsAreFilled(Gebruiker gebruiker){
        return gebruiker.getAchternaam() != null && gebruiker.getGeboorteDatum() != null
                && gebruiker.getGeslacht() != null && gebruiker.getVoornaam() != null;
    }

    /**Methode die alle producten ophaalt van de meegegeven gebruiker.*/
    @GetMapping(value = "/producten")
    public ResponseEntity<Object> GetAllProducten(@AuthenticationPrincipal OAuth2User user){
        String id = user.getAttributes().get("sub").toString();
        List<GebruikerProducten> gebruikerProducten = gebruikerService.GetAllProducten(id);
        List<Product>products = new ArrayList<>();
        for (var element:gebruikerProducten) {
            products.add(element.getProduct());
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    /**Methode die alle gebruikers ophaalt.*/
    @GetMapping(value = "/getAll")
    public ResponseEntity<Object> GetAll(){
        List<Gebruiker> gebruikers = gebruikerService.GetAll();
        return new ResponseEntity<>(gebruikers,HttpStatus.OK);
    }

    /**Methode die een product aanmaakt en koppelt aan de gebruiker die de product aanmaakt.*/
    @PostMapping(value = "/voegProductToe")
    public ResponseEntity<Object> VoegProductToe(@RequestBody GebruikerProducten gebruikerProducten){
        if(Objects.nonNull(gebruikerProducten.gebruiker) || Objects.nonNull(gebruikerProducten.product)){
            this.gebruikerService.VoegProductToe(gebruikerProducten);
            return new ResponseEntity<>(gson.toJson(gson.toJson("Product is succesvol toegevoegd.")),HttpStatus.CREATED);
        }
        return new ResponseEntity<>(gson.toJson(gson.toJson("Er is een fout ontstaan met het toevoeven van de product."))
                ,HttpStatus.BAD_REQUEST);
    }

    /**Methode die een product verwijderd van de meegegeven gebruiker.*/
    @DeleteMapping(value = "/deleteProduct/{id}")
    public ResponseEntity<Object> VerwijderProduct(@PathVariable int id, @AuthenticationPrincipal OAuth2User user){
        String userId = user.getAttributes().get("sub").toString();
        Gebruiker gebruiker = gebruikerService.FindById(userId);
        Product product = this.productService.FindById(id);
        if(product != null && gebruiker != null) {
            this.gebruikerService.VerwijderProduct(userId, product);
            this.productService.Delete(product);
            return new ResponseEntity<>(gson.toJson(gson.toJson("Product is succesvol verwijderd.")), HttpStatus.OK);
        }
        return new ResponseEntity<>(gson.toJson(gson.toJson("Er is iets misgegaan met het verwijderen van het product."))
                ,HttpStatus.BAD_REQUEST);
    }

    /**Methode die de meegegeven gebruiker verwijdert,
     * nadat er is gecontroleerd of de te verwijderen gebruiker wel dezelfde gebruiker is als vanwaar de request vandaan komt*/
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Object> VerwijderGebruiker(@PathVariable String id,@AuthenticationPrincipal OAuth2User user){
        String userId = user.getAttributes().get("sub").toString();
        if(id.equals(userId)){
            if(this.gebruikerService.FindById(id) != null && this.gebruikerService.FindById(userId) != null){
                List<GebruikerProducten> producten = this.gebruikerService.GetAllProducten(id);
                this.gebruikerService.VerwijderAlleProducten(id);
                for (var product:producten) {
                    this.productService.Delete(product.getProduct());
                }
                this.gebruikerService.Delete(this.gebruikerService.FindById(id));
                return new ResponseEntity<>(gson.toJson("Gebruiker is succesvol verwijderd."),HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(gson.toJson("Er is iets misgegaan."),HttpStatus.BAD_REQUEST);
    }
}
