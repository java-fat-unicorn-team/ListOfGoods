package com.spring.core;

import com.spring.core.dao.BasketDao;
import com.spring.core.dao.ListOfGoodsDao;
import com.spring.core.dao.impl.InMemoryBasketDao;
import com.spring.core.model.Product;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BasketTest {

    private static ListOfGoodsDao listOfGoods;

    private static BasketDao basket;

    private static List<Product> list;

    @BeforeClass
    public static void initialBasket() throws Exception {
        list = new ArrayList<Product>() {{
            add(new Product("phone", 790, 340));
            add(new Product("pan", 9, 15));
            add(new Product("computer", 3200, 2100));
        }};
        listOfGoods = Mockito.mock(ListOfGoodsDao.class);
        basket = new InMemoryBasketDao(listOfGoods);
        when(listOfGoods.getProducts()).thenReturn(list);
        when(listOfGoods.getProduct(0)).thenReturn(list.get(0));
        when(listOfGoods.getProduct(1)).thenReturn(list.get(1));
        when(listOfGoods.getProduct(2)).thenReturn(list.get(2));
        for (int i = 0; i < 3; i++) {
            basket.addProduct(i);
        }
    }

    @Test
    public void testGetProductsFromBasket() {
        assertEquals(list, basket.getProductsFromBasket());
    }

    @Test
    public void testGetAllProducts() {
        assertEquals(list, basket.getAllProducts());
    }

    @Test
    public void testGetProduct() {
        try {
            assertEquals(list.get(1), basket.getProduct(1));
        } catch (Exception e) {
            fail("test get product is failed");
        }
    }

    @Test
    public void testUpdateProduct() {
        try {
            basket.updateProduct(2, 1);
            assertEquals(list.get(1), basket.getProduct(2));
        } catch (Exception e) {
            fail("test update product is failed");
        }
    }

    @Test(expected = Exception.class)
    public void testUpdateNonexistentProduct() throws Exception {
        basket.updateProduct(9, 1);
    }

    @Test
    public void testAddProduct() {
        try {
            basket.addProduct(2);
            assertEquals(list.get(2), basket.getProduct(3));
        } catch (Exception e) {
            fail("test add product is failed");
        }
    }

    @Test(expected = Exception.class)
    public void testDeleteProduct() throws Exception {
        basket.deleteProduct(3);
        basket.getProduct(3);
    }
}

