package com.marktplaats.repository;

import com.marktplaats.model.GebruikerProducten;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GebruikerProductenRepo extends JpaRepository<GebruikerProducten,Integer> {

    //List<GebruikerProducten> findGebruikerProductenByGebruiker_Id(int id);

    List<GebruikerProducten> findAllByGebruiker_Id(int id);
    void removeGebruikerProductenByGebruiker_IdAndProduct_Id(int gebrId, int productId);

}
