package com.spring.core;

import com.spring.core.dao.impl.ListOfGoodsDaoImpl;
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
    private ListOfGoods list;
    @InjectMocks
    private ListOfGoodsDaoImpl listOfGoods;
    private static List<Product> myList;

    @BeforeClass
    public static void initialListOfGoods() {
        myList = new ArrayList<>() {{
            add(new Product("phone", new BigDecimal(790.22), 340));
            add(new Product("pan", new BigDecimal(9.12), 15));
            add(new Product("computer", new BigDecimal(3200.00), 2100));
        }};
    }

    @Test
    public void testGetProducts() {
        when(list.getListOfGoods()).thenReturn(myList);
        assertNotNull("the method returned null",listOfGoods.getProducts());
        assertEquals(myList.get(0).getWeight(), listOfGoods.getProducts().get(0).getWeight());
        assertEquals(myList.get(1).getPrice(), listOfGoods.getProducts().get(1).getPrice());
        assertEquals(myList.get(2).getName(), listOfGoods.getProducts().get(2).getName());
    }

    @Test
    public void testGetProductsNull() {
        when(list.getListOfGoods()).thenReturn(null);
        assertNull("the method is failed",listOfGoods.getProducts());
    }

    @Test
    public void testGetProduct() throws Exception {
        when(list.getListOfGoods()).thenReturn(myList);
        assertNotNull("the method returned null",listOfGoods.getProducts());
        assertEquals(myList.get(0).getWeight(), listOfGoods.getProduct(0).getWeight());
        assertEquals(myList.get(1).getPrice(), listOfGoods.getProduct(1).getPrice());
        assertEquals(myList.get(2).getName(), listOfGoods.getProduct(2).getName());
    }

    @Test(expected = Exception.class)
    public void testGetNonexistentProduct() throws Exception {
        when(list.getListOfGoods()).thenReturn(myList);
        listOfGoods.getProduct(4);
    }
}
