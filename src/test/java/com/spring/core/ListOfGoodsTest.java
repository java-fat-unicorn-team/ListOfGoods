package com.spring.core;

import com.spring.core.dao.impl.InMemoryListOfGoodsDao;
import com.spring.core.model.ListOfGoods;
import com.spring.core.model.Product;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ListOfGoodsTest {
    @Mock
    private ListOfGoods listOfGoods;
    @InjectMocks

    private InMemoryListOfGoodsDao listOfGoodsDao;
    private static List<Product> productsList;

    @BeforeClass
    public static void initialListOfGoods() {
        productsList = new ArrayList<>() {{
            add(new Product("phone", new BigDecimal(790.22), 340));
            add(new Product("pan", new BigDecimal(9.12), 15));
            add(new Product("computer", new BigDecimal(3200.00), 2100));
        }};
    }

    @Test
    public void testGetProducts() {
        when(listOfGoods.getListOfGoods()).thenReturn(productsList);
        assertNotNull("the method returned null", listOfGoodsDao.getProducts());
        assertEquals(productsList.get(0).getWeight(), listOfGoodsDao.getProducts().get(0).getWeight());
        assertEquals(productsList.get(1).getPrice(), listOfGoodsDao.getProducts().get(1).getPrice());
        assertEquals(productsList.get(2).getName(), listOfGoodsDao.getProducts().get(2).getName());
    }

    @Test
    public void testGetProductsNull() {
        when(listOfGoods.getListOfGoods()).thenReturn(null);
        assertNull("the method is failed", listOfGoodsDao.getProducts());
    }

    @Test
    public void testGetProduct() throws Exception {
        when(listOfGoods.getListOfGoods()).thenReturn(productsList);
        assertNotNull("the method returned null", listOfGoodsDao.getProducts());
        assertEquals(productsList.get(0).getWeight(), listOfGoodsDao.getProduct(0).getWeight());
        assertEquals(productsList.get(1).getPrice(), listOfGoodsDao.getProduct(1).getPrice());
        assertEquals(productsList.get(2).getName(), listOfGoodsDao.getProduct(2).getName());
    }

    @Test(expected = Exception.class)
    public void testGetNonexistentProduct() throws Exception {
        when(listOfGoods.getListOfGoods()).thenReturn(productsList);
        listOfGoodsDao.getProduct(4);
    }
}
