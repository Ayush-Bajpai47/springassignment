package com.zemoso.springbootassignment.controller;

import com.zemoso.springbootassignment.entity.Product;
import com.zemoso.springbootassignment.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping("/products")
    public List<Product> getProducts(){
        return productService.findAllProducts();
    }
    @GetMapping("/products/{productId}")
    public Product getSingleProduct(@PathVariable int productId){
        Product theProduct=productService.findById(productId);
        if(theProduct==null)
        {
            throw  new RuntimeException("Product id not found -"+productId);
        }
        return theProduct;
    }
    @PostMapping("/products")
    public Product addProduct(@RequestBody Product theProduct){
        theProduct.setId(0);
        productService.save(theProduct);
        return theProduct;
    }

    //updating Products
    @PutMapping("/products")
    public Product updateProduct(@RequestBody Product theProduct){
        productService.save(theProduct);
        return theProduct;
    }
    //deleting customer
    @DeleteMapping("/products/{productId}")
    public String deleteProduct(@PathVariable int productId){
        Product theProduct=productService.findById(productId);
        if(theProduct==null)
        {
            throw new RuntimeException("Product id not found - "+productId);
        }
        productService.deleteById(productId);
        return "Deleted product with Id -"+productId;
    }

}
