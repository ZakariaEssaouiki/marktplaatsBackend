package com.marktplaats.service;

import com.marktplaats.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    public void Create(Product product);
    public void Delete(Product product);
    public void Update(Product product);
    public List<Product> GetAll();
    public Optional<Product> FindById(int id);
}
