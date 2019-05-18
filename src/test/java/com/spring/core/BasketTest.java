package com.spring.core;

import com.spring.core.dao.impl.InMemoryBasketDaoImpl;
import com.spring.core.model.Product;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BasketTest {
//    private static final String[] name = {"phone", "pan", "computer"};
//    private static final int[] price = {790, 9, 3200};
//    private static final int[] weight = {340, 15, 2100};
//    private static InMemoryBasketDaoImpl basket;
//    private static List<Product> list;
//
//    @BeforeClass
//    public static void initialBasket() {
//        list = new ArrayList<Product>() {{
//            for (int i = 0; i < 3; i++)
//                add(new Product(name[i], price[i], weight[i]));
//        }};
//       // basket = new InMemoryBasketDaoImpl(list);
//    }
//
//    @Test
//    public void testGetProducts() {
//        assertEquals(basket.getProducts(), list);
//    }
//
//    @Test
//    public void testGetProduct() {
//        assertEquals(basket.getProduct(1), list.get(1));
//    }
//
//    @Test
//    public void testUpdateProduct() {
//        basket.updateProduct(2, list.get(0));
//        assertEquals(basket.getProduct(2), list.get(0));
//    }
//
//    @Test
//    public void testAddProduct() {
//        basket.addProduct(list.get(1));
//        assertEquals(basket.getProduct(3), list.get(1));
//    }
//
//    @Test(expected = IndexOutOfBoundsException.class)
//    public void testDeleteProduct() {
//        basket.deleteProduct(3);
//        basket.getProduct(3);
//    }
}
