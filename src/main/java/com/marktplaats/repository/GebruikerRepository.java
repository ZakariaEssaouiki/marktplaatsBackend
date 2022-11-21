package com.marktplaats.repository;

import com.marktplaats.model.Gebruiker;
import com.marktplaats.model.Product;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GebruikerRepository extends JpaRepository<Gebruiker, String> {
    boolean findGebruikerByEmail(String email);
    Gebruiker findGebruikerById(String id);
    Gebruiker findGebruikerByGebruikersnaam(String gebruikersnaam);

    boolean existsGebruikerByGebruikersnaam(String gebruikersnaam);
}
