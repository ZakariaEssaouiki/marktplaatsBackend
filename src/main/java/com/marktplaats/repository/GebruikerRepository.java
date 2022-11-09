package com.marktplaats.repository;

import com.marktplaats.model.Gebruiker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GebruikerRepository extends JpaRepository<Gebruiker, Integer> {
    boolean findGebruikerByEmail(String email);
    boolean findGebruikerByGebruikersnaam(String gebruikersnaam);
    Optional<Gebruiker> findGebruikerByGebruikersnaamAndWachtwoordOrEmailAndWachtwoord(String gebrOfEmail, String wachtwoord);

}
