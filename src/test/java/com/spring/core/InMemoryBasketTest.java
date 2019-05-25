package com.spring.core;

import com.spring.core.dao.ListOfGoodsDao;
import com.spring.core.dao.impl.InMemoryBasketDao;
import com.spring.core.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryBasketTest {

    @Mock
    private ListOfGoodsDao listOfGoodsDao;
    @InjectMocks
    private InMemoryBasketDao basket;

    private List<Product> listOfGoods;

    @Before
    public void initialBasket() throws Exception {
        listOfGoods = new ArrayList<>() {{
            add(new Product("phone", new BigDecimal(790.22), 340));
            add(new Product("pan", new BigDecimal(9.12), 15));
            add(new Product("computer", new BigDecimal(3200.00), 2100));
        }};
        when(listOfGoodsDao.getProducts()).thenReturn(listOfGoods);
        when(listOfGoodsDao.getProduct(0)).thenReturn(listOfGoods.get(0));
        when(listOfGoodsDao.getProduct(1)).thenReturn(listOfGoods.get(1));
        when(listOfGoodsDao.getProduct(2)).thenReturn(listOfGoods.get(2));
        for (int i = 0; i < 3; i++) {
            basket.addProduct(i);
        }
    }

    @Test
    public void testGetProductsFromBasket() {
        assertEquals(listOfGoods.get(1).getPrice(), basket.getProductsFromBasket().get(1).getPrice());
        assertEquals(listOfGoods.get(1).getWeight(), basket.getProductsFromBasket().get(1).getWeight());
    }

    @Test
    public void testGetAllProducts() {
        assertEquals(listOfGoods, basket.getAllProducts());
        assertEquals(listOfGoods.get(1).getName(), basket.getAllProducts().get(1).getName());
        assertEquals(listOfGoods.get(1).getWeight(), basket.getAllProducts().get(1).getWeight());
    }

    @Test
    public void testGetProduct() {
        assertEquals(listOfGoods.get(1), basket.getProduct(1));
        assertEquals(listOfGoods.get(1).getPrice(), basket.getProduct(1).getPrice());
        assertEquals(listOfGoods.get(1).getWeight(), basket.getProduct(1).getWeight());
    }

    @Test(expected = Exception.class)
    public void testGetNonexistentProduct() {
        int size = basket.getProductsFromBasket().size();
        basket.getProduct(size);
    }

    @Test
    public void testUpdateProduct() throws Exception {
        basket.updateProduct(2, 1);
        assertEquals(listOfGoods.get(1), basket.getProduct(2));
    }

    @Test(expected = Exception.class)
    public void testUpdateNonexistentProduct() throws Exception {
        int size = basket.getProductsFromBasket().size();
        basket.updateProduct(size, 1);
    }

    @Test(expected = Exception.class)
    public void testUpdateOnNonexistentProduct() throws Exception {
        when(listOfGoodsDao.getProduct(10)).thenThrow(Exception.class);
        basket.updateProduct(1, 10);
    }

    @Test
    public void testAddProduct() throws Exception {
        basket.addProduct(2);
        assertEquals(listOfGoods.get(2), basket.getProduct(3));
        assertEquals(listOfGoods.get(1).getName(), basket.getAllProducts().get(1).getName());
        assertEquals(listOfGoods.get(1).getWeight(), basket.getAllProducts().get(1).getWeight());
    }

    @Test(expected = Exception.class)
    public void testAddNonexistentProduct() throws Exception {
        when(listOfGoodsDao.getProduct(10)).thenThrow(Exception.class);
        basket.addProduct(10);
    }

    @Test
    public void testDeleteProduct() throws Exception {
        int sizeBefore = basket.getProductsFromBasket().size();
        basket.deleteProduct(2);
        int sizeAfter = basket.getProductsFromBasket().size();
        assertFalse(sizeBefore == sizeAfter);
    }

    @Test(expected = Exception.class)
    public void testDeleteNonexistentProduct() throws Exception {
        int size = basket.getProductsFromBasket().size();
        basket.deleteProduct(size);
    }
}

