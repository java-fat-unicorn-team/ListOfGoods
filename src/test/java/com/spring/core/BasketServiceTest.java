package com.spring.core;

import com.spring.core.dao.BasketDao;
import com.spring.core.dao.ListOfGoodsDao;
import com.spring.core.dao.impl.InMemoryBasketDao;
import com.spring.core.model.Product;
import com.spring.core.service.BasketService;
import com.spring.core.service.impl.BasketServiceImpl;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BasketServiceTest {

    private static ListOfGoodsDao listOfGoods;
    private static BasketDao basket;
    private static BasketService basketService;

    private static List<Product> list;

    @BeforeClass
    public static void initialProductService() {
        list = new ArrayList<>() {{
            add(new Product("phone", 790, 340));
            add(new Product("pan", 9, 15));
            add(new Product("computer", 3200, 2100));
        }};

        listOfGoods = Mockito.mock(ListOfGoodsDao.class);
        basket = new InMemoryBasketDao(listOfGoods);
        basketService = new BasketServiceImpl(basket);
        when(listOfGoods.getProducts()).thenReturn(list);
        when(listOfGoods.getProduct(0)).thenReturn(list.get(0));
        when(listOfGoods.getProduct(1)).thenReturn(list.get(1));
        when(listOfGoods.getProduct(2)).thenReturn(list.get(2));
        for (int i = 0; i < 3; i++) {
            basketService.addProduct(i);
        }
    }

    @Test
    public void testGetProductsFromBasket() {
        assertEquals(list, basketService.getProductsFromBasket());
    }

    @Test
    public void testGetAllProducts() {
        assertEquals(list, basketService.getAllProducts());
    }

    @Test
    public void testGetProduct() {
        assertEquals(list.get(1), basketService.getProduct(1));
    }

    @Test
    public void testUpdateProduct() {
        basketService.updateProduct(2, 1);
        assertEquals(list.get(1), basketService.getProduct(2));
    }

    @Test
    public void testAddProduct() {
        basketService.addProduct(2);
        assertEquals(list.get(2), basketService.getProduct(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDeleteProduct() {
        basketService.deleteProduct(3);
        basketService.getProduct(3);
    }
}
