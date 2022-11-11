package com.marktplaats.Service;

import com.marktplaats.model.Gebruiker;
import com.marktplaats.model.GebruikerProducten;
import com.marktplaats.model.Geslacht;
import com.marktplaats.model.Product;
import com.marktplaats.repository.GebruikerProductenRepo;
import com.marktplaats.repository.GebruikerRepository;
import com.marktplaats.service.GebruikerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GebruikerServiceTest {

    @Mock
    private GebruikerRepository repo;
    @Mock
    private GebruikerProductenRepo gebruikerProductenRepo;
    private GebruikerService gebruikerService;

    @BeforeEach
    void setUp(){
        this.gebruikerService = new GebruikerService(repo, gebruikerProductenRepo);
    }

    @Test
    void findById() {
        //when
        gebruikerService.FindById(100);
        //then
        verify(repo).findById(100);
        verify(repo, Mockito.never()).deleteById(100);
    }

    @Test
    void getAll() {
        //when
        gebruikerService.GetAll();
        //then
        verify(repo).findAll();
    }

    @Test
    void create() {
        //when
        Gebruiker gebruiker = new Gebruiker("jan01","jan@example.com","Jan123","jan","janssen",
                LocalDate.now(), Geslacht.Man,100);
        gebruikerService.Create(gebruiker);
        //then
        //gebruiker die wordt aangemaakt en opgeslagen wordt onderschept.
        ArgumentCaptor<Gebruiker> argumentCaptor =
                ArgumentCaptor.forClass(Gebruiker.class);
        verify(repo).save(argumentCaptor.capture());
        Gebruiker capturedGebruiker = argumentCaptor.getValue();
        //Controleren of dezelfde gebruiker wordt aangemaakt.
        assertThat(capturedGebruiker).isEqualTo(gebruiker);
    }

    @Test
    void delete() {
        //when
        Gebruiker gebruiker = new Gebruiker("jan01","jan@example.com","Jan123","jan","janssen",
                LocalDate.now(), Geslacht.Man,100);
        gebruikerService.Delete(gebruiker);
        //then
        verify(repo).delete(gebruiker);
    }

    @Test
    void gebruikersnaamAlInGebruik() {
        //when
        gebruikerService.GebruikersnaamAlInGebruik("jan");
        //then
        verify(repo).findGebruikerByGebruikersnaam("jan");
    }

    @Test
    void checkIfGebruikersnaamIsNullOfLeeg() {
        //when
        gebruikerService.GebruikersnaamAlInGebruik(" ");
        //then
        verify(repo, never()).findGebruikerByGebruikersnaam(" ");
    }

    @Test
    void emailAlInGebruik() {
        //when
        gebruikerService.EmailAlInGebruik("jan@example.com");
        //then
        verify(repo).findGebruikerByEmail("jan@example.com");
    }

    @Test
    void chechIfEmailIsNullOfLeeg() {
        //when
        gebruikerService.EmailAlInGebruik("");
        //then
        verify(repo, never()).findGebruikerByEmail("");
    }

    @Test
    void login() {
        //when
        gebruikerService.Login("ludo101","Welkom101");
        //then
        verify(repo).findGebruikerByGebruikersnaamAndWachtwoordOrEmailAndWachtwoord("ludo101","Welkom101","ludo101","Welkom101");
        verify(repo,never()).findGebruikerByGebruikersnaamAndWachtwoordOrEmailAndWachtwoord(" "," ","","");
    }

    @Test
    void getAllProducten() {
        //when
        gebruikerService.GetAllProducten(10);
        //then
        verify(gebruikerProductenRepo).findAllByGebruiker_Id(10);
    }

    @Test
    void voegProductToe() {
        //when
        Product product = new Product("rode fiets",10,"Fiets is nog in goede staat", LocalDate.now(),100);
        Gebruiker gebruiker = new Gebruiker("jan01","jan@example.com","Jan123","jan","janssen",
                LocalDate.now(), Geslacht.Man,100);
        GebruikerProducten gebruikerProducten = new GebruikerProducten();
        gebruikerProducten.setProduct(product);
        gebruikerProducten.setGebruiker(gebruiker);
        gebruikerService.VoegProductToe(gebruikerProducten);
        //then
        ArgumentCaptor<GebruikerProducten> argumentCaptor =
                ArgumentCaptor.forClass(GebruikerProducten.class);
        verify(gebruikerProductenRepo).save(argumentCaptor.capture());
        GebruikerProducten capturedGebruiker = argumentCaptor.getValue();
        //Controleren of dezelfde gebruiker wordt aangemaakt.
        assertThat(capturedGebruiker).isEqualTo(gebruikerProducten);
    }

    @Test
    void verwijderProduct() {
        //when
        Product product = new Product("rode fiets",10,"Fiets is nog in goede staat", LocalDate.now(),100);
        Gebruiker gebruiker = new Gebruiker("jan01","jan@example.com","Jan123","jan","janssen",
                LocalDate.now(), Geslacht.Man,100);
        gebruikerService.VerwijderProduct(gebruiker.getId(),product);
        //then
        verify(gebruikerProductenRepo).removeGebruikerProductenByGebruiker_IdAndProduct_Id(gebruiker.getId(),product.getId());
    }
}