package com.zemoso.springbootassignment.service;

import com.zemoso.springbootassignment.entity.Product;
import com.zemoso.springbootassignment.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService=new ProductServiceImpl();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void testGetAllProducts(){
        List<Product> mockProducts=new ArrayList<>();
        mockProducts.add(new Product());
        Mockito.when(productRepository.findAll()).thenReturn(mockProducts);
        List<Product> products=productService.findAllProducts();
        assertEquals(mockProducts,products);

    }
    @Test
    public void testGetProductById(){
        Product mockProduct=new Product();

        mockProduct.setId(1);
        mockProduct.setProductName("Air conditioner");
        mockProduct.setProductType("ELectronics");
        mockProduct.setStock(22);
        mockProduct.setPrice(23.0);
        mockProduct.setDescription("No desc");
        Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(mockProduct));
Product product=productService.findById(1);
assertEquals(mockProduct,product
);
    }

    @Test(expected = RuntimeException.class )
    public void testGetProductById_Exception(){


        Mockito.when(productRepository.findById(11)).thenReturn(Optional.empty());

        productService.findById(11);
    }
@Test
    public void testSaveProduct(){
Product product=new Product();
product.setDescription("Electronics products");
product.setProductName("DVD");
product.setStock(22);
product.setPrice(232);
product.setProductType("electronics");
Mockito.when(productRepository.save(product)).thenReturn(product);
Product savedproduct=productService.save(product);
assertEquals(product,savedproduct);


}


    @Test
    public void testDeleteById(){
        Product product=new Product();
        product.setId(2);
        product.setDescription("Electronics products");
        product.setProductName("DVD");
        product.setStock(22);
        product.setPrice(232);
        product.setProductType("electronics");
        doNothing().when(productRepository).deleteById(anyInt());
//        Mockito.when(productRepository.deleteById(2)).thenReturn(void);
   //     Mockito.when(productRepository.findById(2)).thenReturn(Optional.of(product));
       productService.deleteById(product.getId());

        verify(productRepository).deleteById(product.getId());
        verify(productRepository,times(1)).deleteById(product.getId());

//        int productId = 1;
//        productService.deleteById(productId);
//        verify(productRepository).deleteById(productId);

    }


    @Test
    public void testDeleteProductById_Exception(){



        doThrow(RuntimeException.class).when(productRepository).deleteById(anyInt());
        assertThrows(RuntimeException.class, () -> productService.deleteById(11));
        verify(productRepository).deleteById(11);
    }
}
