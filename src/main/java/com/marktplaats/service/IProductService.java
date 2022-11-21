package com.marktplaats.service;

import com.marktplaats.model.Product;

import java.util.Collection;
import java.util.Optional;

public interface IProductService {

    public void Create(Product product);
    public void Delete(Product product);
    public void Update(Product product);
    public Collection<Product> GetAll();
    public Product FindById(int id);
}
