package com.spring.core;

import com.spring.core.dao.ListOfGoodsDao;
import com.spring.core.dao.impl.InMemoryListOfGoodsDao;
import com.spring.core.model.ListOfGoods;
import com.spring.core.model.Product;
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
public class ListOfGoodsTest {
    private static ListOfGoodsDao listOfGoods;
    private static ListOfGoods list;
    private static List<Product> myList;

    @BeforeClass
    public static void initialListOfGoods() {
        myList = new ArrayList<Product>() {{
            add(new Product("phone", 790, 340));
            add(new Product("pan", 9, 15));
            add(new Product("computer", 3200, 2100));
        }};
        list = Mockito.mock(ListOfGoods.class);
        listOfGoods = new InMemoryListOfGoodsDao(list);
        when(list.getListOfGoods()).thenReturn(myList);
    }

    @Test
    public void testGetProducts() {
        assertEquals(myList, listOfGoods.getProducts());
    }

    @Test(expected = Exception.class)
    public void testGetProduct() throws Exception {
        listOfGoods.getProduct(4);
    }
}
