package com.marktplaats.controller;

import com.marktplaats.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductService.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private ProductService productService;
    private ProductController controller;

    @BeforeEach
    void setUp(){
        this.productService = Mockito.mock(ProductService.class);
        this.controller = new ProductController(productService);
    }

    @Test
    void getAll() throws Exception {
        //when
        this.controller.GetAll();
        //then
        verify(productService).GetAll();

    }
}