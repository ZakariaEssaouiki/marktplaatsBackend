package com.marktplaats.controller;

import com.google.gson.Gson;
import com.marktplaats.model.Product;
import com.marktplaats.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@CrossOrigin
public class ProductController {

    @Autowired
    private ProductService productService;
    final private Gson gson = new Gson();
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    public ResponseEntity<Object> GetAll() {
        return new ResponseEntity<>(productService.GetAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<Object> VoegProductToe(@RequestBody Product product){
        this.productService.Create(product);
        return new ResponseEntity<>(gson.toJson("Product succesvol aangemaakt"),HttpStatus.OK);
    }

}
