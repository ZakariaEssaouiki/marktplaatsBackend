package com.marktplaats.controller;

import com.marktplaats.model.Gebruiker;
import com.marktplaats.model.GebruikerProducten;
import com.marktplaats.model.Geslacht;
import com.marktplaats.model.Product;
import com.marktplaats.service.GebruikerService;
import com.marktplaats.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@WebMvcTest(GebruikerService.class)
class GebruikerControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private GebruikerService gebruikerService;
    private ProductService productService;
    private GebruikerController controller;

    @BeforeEach
    void setUp(){
        this.gebruikerService = Mockito.mock(GebruikerService.class);
        this.controller = new GebruikerController(gebruikerService, productService);
    }

    @Test
    void getAllProducten() throws Exception {
        //given
        Gebruiker gebruiker = new Gebruiker("jan01","jan@example.com","jan","janssen",
                LocalDate.now(), Geslacht.Man,"100");
        //when
        //this.gebruikerService.Create(gebruiker);
        this.controller.GetAllProducten(null);
        //then
        verify(gebruikerService).GetAllProducten(gebruiker.getId());
        /*mvc.perform(MockMvcRequestBuilders.get("/producten/{id}",1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));*/
    }

    @Test
    void getAll() throws Exception {
        //when
        this.controller.GetAll();
        //then
        verify(gebruikerService).GetAll();
        /*mvc.perform(MockMvcRequestBuilders.get("/getAll")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.gebruikers[*].id").isNotEmpty());*/
    }

    @Test
    void voegProductToe() {
        //given
        Gebruiker gebruiker = new Gebruiker("jan01","jan@example.com","jan","janssen",
                LocalDate.now(), Geslacht.Man,"100");
        Product product = new Product("rode fiets",10,"Fiets is nog in goede staat", LocalDate.now(),100);
        Gebruiker gebruikerFalse = null;
        GebruikerProducten gebruikerProducten = new GebruikerProducten();
        gebruikerProducten.setGebruiker(gebruiker);
        gebruikerProducten.setProduct(product);
        GebruikerProducten gebruikerProductenFalse = new GebruikerProducten();
        gebruikerProducten.setGebruiker(gebruikerFalse);
        gebruikerProducten.setProduct(product);
        //when
        this.controller.VoegProductToe(gebruikerProducten);
        this.controller.VoegProductToe(gebruikerProductenFalse);
        //then
        verify(gebruikerService).VoegProductToe(gebruikerProducten);
        verify(gebruikerService,never()).VoegProductToe(gebruikerProductenFalse);
    }

    @Test
    void verwijderProduct() {
        //given
        Gebruiker gebruiker = new Gebruiker("jan01","jan@example.com","jan","janssen",
                LocalDate.now(), Geslacht.Man,"100");
        Product product = new Product("rode fiets",10,"Fiets is nog in goede staat", LocalDate.now(),100);
        //when
        this.controller.VerwijderProduct(product.getId(),null);
        this.controller.VerwijderProduct(product.getId(),null);
        //then
        verify(gebruikerService).VerwijderProduct(gebruiker.getId(),product);
        verify(gebruikerService,never()).VerwijderProduct(gebruiker.getId(),null);
    }
}