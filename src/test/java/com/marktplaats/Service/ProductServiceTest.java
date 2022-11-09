package com.marktplaats.Service;

import com.marktplaats.model.Product;
import com.marktplaats.repository.ProductRepository;
import com.marktplaats.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repo;

    private ProductService productService;

    @BeforeEach
    void setUp(){
        this.productService = new ProductService(repo);
    }

    @Test
    void create() {
        //when
        Product product = new Product("rode fiets",10,"Fiets is nog in goede staat", LocalDate.now(),100);
        this.productService.Create(product);
        //then
        verify(repo).save(product);
    }

    @Test
    void delete() {
        //when
        Product product = new Product("rode fiets",10,"Fiets is nog in goede staat", LocalDate.now(),100);
        this.productService.Delete(product);
        //then
        verify(repo).delete(product);
    }

    @Test
    void update() {
        //when
        Product product = new Product("rode fiets",10,"Fiets is nog in goede staat", LocalDate.now(),100);
        this.productService.Update(product);
        //then
        verify(repo).save(product);
        verify(repo, never()).delete(product);
    }

    @Test
    void getAll() {
        //when
        this.productService.GetAll();
        //then
        verify(repo).findAll();
    }

    @Test
    void findById() {
        //when
        this.productService.FindById(10);
        //then
        verify(repo).findById(10);
        verify(repo, never()).deleteById(10);
    }
}