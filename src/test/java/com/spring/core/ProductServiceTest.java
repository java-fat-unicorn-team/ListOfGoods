package com.spring.core;

import com.spring.core.dao.impl.InMemoryBasketDaoImpl;
import com.spring.core.model.Product;
import com.spring.core.service.ProductService;
import com.spring.core.service.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class ProductServiceTest {
    private static ProductService productService;
    private static InMemoryBasketDaoImpl basket;

    @BeforeClass
    public static void initialProductService() {
        String[] name = {"phone", "pan", "computer"};
        int[] price = {790, 9, 3200};
        int[] weight = {340, 15, 2100};

        basket = new InMemoryBasketDaoImpl(new ArrayList<Product>() {{
            for (int i = 0; i < 3; i++)
                add(new Product(name[i], price[i], weight[i]));
        }});
        productService = new ProductServiceImpl(basket);
    }

    @Before
    @Test
    public void testGetProducts() {
        assertEquals(productService.getProducts(), basket.getProducts());
    }

    @Test
    public void testGetProduct() {
        assertEquals(productService.getProduct(1), basket.getProduct(1));
    }

    @Test
    public void testUpdateProduct() {
        basket.updateProduct(2, basket.getProduct(0));
        assertEquals(productService.getProduct(2), basket.getProduct(0));
    }

    @Test
    public void testAddProduct() {
        productService.addProduct(basket.getProduct(1));
        assertEquals(productService.getProduct(3), basket.getProduct(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDeleteProduct() {
        productService.deleteProduct(3);
        productService.getProduct(3);
    }
}
