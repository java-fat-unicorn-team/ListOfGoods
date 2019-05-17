package com.spring.core;

import com.spring.core.dao.impl.InMemoryBasketDaoImpl;
import com.spring.core.model.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class testBasket {
    private String[] name = {"phone", "pan", "computer"};
    private int[] price = {790, 9, 3200};
    private int[] weight = {340, 15, 2100};
    InMemoryBasketDaoImpl basket;
    List<Product> list= new ArrayList<Product>() {{
        for (int i = 0; i < 3; i++)
            add(new Product(name[i], price[i], weight[i]));
    }};

    @Before
    public void initialBasket() {
        basket = new InMemoryBasketDaoImpl(list);
    }

    @Test
    public void testGetProducts(){
        assertEquals(basket.getProducts(), list);
    }

    @Test
    public void testGetProduct() {
        assertEquals(basket.getProduct(1), list.get(1));
    }

    @Test
    public void testUpdateProduct() {
        basket.updateProduct(2, list.get(0));
        assertEquals(basket.getProduct(2), list.get(0));
    }

    @Test
    public void testAddProduct() {
        basket.addProduct(list.get(1));
        assertEquals(basket.getProduct(3), list.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDeleteProduct() {
        basket.deleteProduct(3);
        basket.getProduct(3);
    }
}
