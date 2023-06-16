package com.zemoso.springbootassignment.controller;


import com.zemoso.springbootassignment.entity.Product;
import com.zemoso.springbootassignment.service.ProductService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }
    @Test
    public void testFindAllProducts() throws Exception{
        List<Product> products=new ArrayList<>();
        products.add(new Product("bat","sports",12,15.02,"sggg"));
        products.add(new Product("bat","sports",12,15.02,"sggg"));
        when(productService.findAllProducts()).thenReturn(products);

        List<Product> actualProductList=productController.getProducts();
        assertEquals(actualProductList,products);

    }
    @Test
    public void testSingleProductFindById() throws Exception{
        Product mockProduct=new Product();

        mockProduct.setId(1);
        mockProduct.setProductName("Air conditioner");
        mockProduct.setProductType("ELectronics");
        mockProduct.setStock(22);
        mockProduct.setPrice(23.0);
        mockProduct.setDescription("No desc");
        when(productService.findById(1)).thenReturn(mockProduct);
   Product actualProductList=productController.getSingleProduct(1);
        assertEquals(actualProductList,mockProduct);
    }
    @Test(expected = RuntimeException.class )
    public void testGetProductById_Exception(){


        Mockito.when(productService.findById(11)).thenReturn(null);

        productController.getSingleProduct(11);
    }
    @Test
    public void testSaveProduct(){
        Product product=new Product();
        product.setDescription("Electronics products");
        product.setProductName("DVD");
        product.setStock(22);
        product.setPrice(232);
        product.setProductType("electronics");
        Mockito.when(productService.save(product)).thenReturn(product);
        Product savedproduct=productController.addProduct(product);
        Assertions.assertEquals(product,savedproduct);
    }
    @Test
    public void testUpdateProduct(){
        Product product=new Product();
        product.setDescription("Electronics products");
        product.setProductName("DVD");
        product.setStock(22);
        product.setPrice(232);
        product.setId(2);
        product.setProductType("electronics");
        Mockito.when(productService.save(product)).thenReturn(product);
        Product savedproduct=productController.updateProduct(product);
        Assertions.assertEquals(product,savedproduct);
    }
    @Test
    public void testDeleteProduct() throws Exception {
        int productId = 1;
        when(productService.findById(productId)).thenReturn(new Product());
        productController.deleteProduct(productId);
        verify(productService).findById(productId);
        verify(productService).deleteById(productId);
    }

    @Test(expected = RuntimeException.class )
    public void testDeleteProductById_Exception(){


        Mockito.when(productService.findById(11)).thenReturn(null);

        productController.deleteProduct(11);
    }


}
