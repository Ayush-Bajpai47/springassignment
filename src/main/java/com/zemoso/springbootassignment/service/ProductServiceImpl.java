package com.zemoso.springbootassignment.service;

import com.zemoso.springbootassignment.entity.Product;
import com.zemoso.springbootassignment.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class  ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;


    @Override
    public  List<Product> findAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(int theId) {
        Optional<Product>result=productRepository.findById(theId);
        Product product=null;
        if(result.isPresent())
        {
        product=result.get();
        }
        else {
            throw  new RuntimeException("Product with ID not found -"+theId);
        }
        return product;
    }

    @Override
    public Product save(Product theProduct) {
return productRepository.save(theProduct);

    }

    @Override
    public void deleteById(int theId) {

        System.out.println("hello");
        productRepository.deleteById( theId);
    }
}
