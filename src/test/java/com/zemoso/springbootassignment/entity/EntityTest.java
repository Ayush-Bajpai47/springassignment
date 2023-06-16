package com.zemoso.springbootassignment.entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EntityTest {
    @Test
    public void testAllArgsConstructor() {
        Product abc = new Product(1, "mario","games",12,2.0,"play");

        assertNotNull(abc);

    }
    @Test
    public void testGetter() {
        Product myClass = new Product();


        myClass.setProductName("chess");
        myClass.setStock(12);
        myClass.setId(1);
        myClass.setPrice(12.03);
        String expectedPrice=String.valueOf(12.03);
        double price=myClass.getPrice();
        String actualprice=String.valueOf(price);
myClass.setDescription("games product");
        myClass.setProductType("games");
        String name = myClass.getProductName();
        int stock=myClass.getStock();
        int id=myClass.getId();
String desc=myClass.getDescription();
        String productType= myClass.getProductType();
        myClass.setProductName("chess");
     assertEquals(12,stock);
assertEquals("games product",desc);
     assertEquals("games",productType);
        assertEquals("chess", name);
        assertEquals(expectedPrice,actualprice);
    }
}



