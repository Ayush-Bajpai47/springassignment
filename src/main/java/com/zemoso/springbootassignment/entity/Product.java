package com.zemoso.springbootassignment.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    @Setter
    @Getter
    private int id;
    @Column(name="product_name")
    @Setter
    @Getter
    private String productName;
    @Column(name="product_type")
    @Setter
    @Getter
    private String productType;
    @Column(name="stock")
    @Setter
    @Getter
    private int stock;
    @Column(name="price")
    @Setter
    @Getter
    private double price;
    @Column(name="description")
    @Setter
    @Getter
    private String description;

    public Product(String productName, String productType, int stock, double price, String description) {
        this.productName = productName;
        this.productType = productType;
        this.stock = stock;
        this.price = price;
        this.description = description;
    }
}
