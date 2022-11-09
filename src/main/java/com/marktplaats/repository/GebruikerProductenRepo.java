package com.marktplaats.repository;

import com.marktplaats.model.GebruikerProducten;
import com.marktplaats.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GebruikerProductenRepo extends JpaRepository<GebruikerProducten,Integer> {

    List<Product> findGebruikerProductenByGebruiker_Id(int id);

    Void removeGebruikerProductenByGebruiker_IdAndProduct_Id(int gebrId, int productId);

}
