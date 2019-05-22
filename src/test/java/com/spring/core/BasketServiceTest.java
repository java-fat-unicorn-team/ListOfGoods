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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BasketServiceTest {

    private static ListOfGoodsDao listOfGoods;
    private static BasketDao basket;
    private static BasketService basketService;

    private static List<Product> list;

    @BeforeClass
    public static void initialProductService() throws Exception {
        list = new ArrayList<>() {{
            add(new Product("phone", new BigDecimal(790.22), 340));
            add(new Product("pan", new BigDecimal(9.12), 15));
            add(new Product("computer", new BigDecimal(3200.00), 2100));
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
        try {
            assertEquals(list.get(1), basketService.getProduct(1));
        } catch (Exception e) {
            fail("test get product is failed");
        }
    }

    @Test
    public void testUpdateProduct() {
        try {
            basketService.updateProduct(2, 1);
            assertEquals(list.get(1), basketService.getProduct(2));
        } catch (Exception e) {
            fail("test update product is failed");
        }
    }

    @Test(expected = Exception.class)
    public void testUpdateNonexistentProduct() throws Exception {
        basketService.updateProduct(9, 1);
    }

    @Test
    public void testAddProduct() {
        try {
            basketService.addProduct(2);
            assertEquals(list.get(2), basketService.getProduct(basketService.getProductsFromBasket().size()-1));
        } catch (Exception e) {
            fail("test add product is failed");
        }
    }

    @Test(expected = Exception.class)
    public void testDeleteProduct() throws Exception {
        int size = basketService.getProductsFromBasket().size()-1;
        basketService.deleteProduct(size);
        basketService.getProduct(size);
    }
}
