package com.zemoso.springbootassignment.service;

import com.zemoso.springbootassignment.entity.Product;

import java.util.List;

public interface ProductService {
public List<Product> findAllProducts();
public Product findById(int theId);
public Product save(Product theProduct);
public void deleteById(int theId);
}
