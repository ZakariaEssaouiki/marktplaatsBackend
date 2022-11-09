package com.marktplaats.controller;

import com.marktplaats.model.Gebruiker;
import com.marktplaats.model.Geslacht;
import com.marktplaats.model.Product;
import com.marktplaats.service.GebruikerService;
import com.marktplaats.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@WebMvcTest
class GebruikerControllerTest {

    @MockBean
    private GebruikerService gebruikerService;
    @MockBean
    private ProductService productService;
    private GebruikerController controller;

    @BeforeEach
    void setUp(){
        this.gebruikerService = Mockito.mock(GebruikerService.class);
        this.productService = Mockito.mock(ProductService.class);
        this.controller = new GebruikerController(gebruikerService);
    }

    @Test
    void getAllProducten() {
        //given
        Gebruiker gebruiker = new Gebruiker("jan01","jan@example.com","Jan123","jan","janssen",
                LocalDate.now(), Geslacht.Man,100);
        //when
        this.controller.GetAllProducten(gebruiker.getId());
        //then
        verify(gebruikerService).GetAllProducten(gebruiker.getId());
    }

    @Test
    void getAll() {
        //when
        this.controller.GetAll();
        //then
        verify(gebruikerService).GetAll();
    }

    @Test
    void voegProductToe() {
        //given
        Gebruiker gebruiker = new Gebruiker("jan01","jan@example.com","Jan123","jan","janssen",
                LocalDate.now(), Geslacht.Man,100);
        Product product = new Product("rode fiets",10,"Fiets is nog in goede staat", LocalDate.now(),100);
        Gebruiker gebruikerFalse = null;
        //when
        this.controller.VoegProductToe(gebruiker,product);
        this.controller.VoegProductToe(gebruikerFalse,product);
        //then
        verify(gebruikerService).VoegProductToe(gebruiker,product);
        verify(gebruikerService,never()).VoegProductToe(gebruikerFalse,product);
    }

    @Test
    void verwijderProduct() {
        //given
        Gebruiker gebruiker = new Gebruiker("jan01","jan@example.com","Jan123","jan","janssen",
                LocalDate.now(), Geslacht.Man,100);
        Product product = new Product("rode fiets",10,"Fiets is nog in goede staat", LocalDate.now(),100);
        //when
        this.controller.VerwijderProduct(gebruiker.getId(),product);
        this.controller.VerwijderProduct(gebruiker.getId(),null);
        //then
        verify(gebruikerService).VerwijderProduct(gebruiker.getId(),product);
        verify(gebruikerService,never()).VerwijderProduct(gebruiker.getId(),null);
    }
}