package com.marktplaats.controller;

import com.marktplaats.service.GebruikerService;
import com.marktplaats.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.verify;

@WebMvcTest
class ProductControllerTest {

    @MockBean
    private ProductService productService;
    @MockBean
    private GebruikerService gebruikerService;
    private ProductController controller;

    @BeforeEach
    void setUp(){
        this.productService = Mockito.mock(ProductService.class);
        this.gebruikerService = Mockito.mock(GebruikerService.class);
        this.controller = new ProductController(productService);
    }

    @Test
    void getAll() {
        //when
        this.controller.GetAll();
        //then
        verify(productService).GetAll();
    }
}