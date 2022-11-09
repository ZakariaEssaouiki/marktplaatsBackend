package com.marktplaats.controller;

import com.marktplaats.model.Product;
import com.marktplaats.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("getAll")
    public ResponseEntity<Object> GetAll() {
        List<Product> producten = productService.GetAll();
        return new ResponseEntity<>(producten, HttpStatus.OK);
    }


}
