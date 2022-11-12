package com.marktplaats.service;

import com.marktplaats.model.Product;
import com.marktplaats.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements IProductService{

    @Autowired
    private ProductRepository repo;

    public ProductService(ProductRepository repo){
        this.repo = repo;
    }
    @Override
    public void Create(Product product) {
        product.setDatumGeplaatst(LocalDate.now());
        this.repo.save(product);
    }

    @Override
    public void Delete(Product product) {
        this.repo.delete(product);
    }

    @Override
    public void Update(Product product) {
        this.repo.save(product);
    }

    @Override
    public List<Product> GetAll() {
        return this.repo.findAll();
    }

    @Override
    public Optional<Product> FindById(int id) {
        Optional<Product> product = this.repo.findById(id);
        return product;
    }
}
