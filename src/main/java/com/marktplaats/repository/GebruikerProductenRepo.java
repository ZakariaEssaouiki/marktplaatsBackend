package com.marktplaats.repository;

import com.marktplaats.model.GebruikerProducten;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GebruikerProductenRepo extends JpaRepository<GebruikerProducten,Integer> {

    GebruikerProducten findGebruikerProductenByProduct_Id(int id);
    List<GebruikerProducten> findAllByGebruiker_Id(String id);
    void removeGebruikerProductenByGebruiker_IdAndProduct_Id(String gebrId, int productId);
    void deleteAllByGebruiker_Id(String id);

}
