package com.spring.core;

import com.spring.core.model.Product;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ModelTest {
    private String name = "phone";
    private int price = 570;
    private int weight = 360;
    Product product;

    @Before
    public void initialProduct() {
        product = new Product(name, price, weight);
    }

    @Test
    public void testGetName() {
        assertEquals(product.getName(), name);
    }

    @Test
    public void testGetPrice() {
        assertEquals(product.getPrice(), price);
    }

    @Test
    public void testGetWeight() {
        assertEquals(product.getWeight(), weight);
    }
}
