package com.spring.core;

import com.spring.core.config.BasketInitializer;
import com.spring.core.dao.ListOfGoodsDao;
import com.spring.core.dao.impl.InFileBasketDao;
import com.spring.core.model.Basket;
import com.spring.core.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InFileBasketTest {

    @Mock
    private Basket list;
    @Mock
    private ListOfGoodsDao listOfGoods;
    @Mock
    private BasketInitializer basketInitializer;
    private InFileBasketDao basket;

    private static List<Product> myList;
    private static List<Product> myList2;

    @Before
    public void initialBasket() throws Exception {
        myList = new ArrayList<>() {{
            add(new Product("phone", new BigDecimal(790.22), 340));
            add(new Product("pan", new BigDecimal(9.12), 15));
            add(new Product("computer", new BigDecimal(3200.00), 2100));
        }};

        myList2 = new ArrayList<>() {{
            add(new Product("phone", new BigDecimal(790.22), 340));
            add(new Product("pan", new BigDecimal(9.12), 15));
            add(new Product("computer", new BigDecimal(3200.00), 2100));
        }};
        basket = new InFileBasketDao(listOfGoods, basketInitializer);
        when(list.getBasket()).thenReturn(myList2);
        basket.setBasket(list);
    }

    @Test
    public void testGetProductsFromBasket() {
        assertEquals(myList.get(1).getPrice(), basket.getProductsFromBasket().get(1).getPrice());
        assertEquals(myList.get(1).getWeight(), basket.getProductsFromBasket().get(1).getWeight());
    }

    @Test
    public void testGetAllProducts() {
        when(listOfGoods.getProducts()).thenReturn(myList);
        assertEquals(myList.get(1).getName(), basket.getAllProducts().get(1).getName());
        assertEquals(myList.get(1).getWeight(), basket.getAllProducts().get(1).getWeight());
    }

    @Test
    public void testGetProduct() {
        assertEquals(myList.get(1).getName(), basket.getProduct(1).getName());
        assertEquals(myList.get(2).getPrice(), basket.getProduct(2).getPrice());
        assertEquals(myList.get(0).getWeight(), basket.getProduct(0).getWeight());
    }

    @Test(expected = Exception.class)
    public void testGetNonexistentProduct() {
        int size = basket.getProductsFromBasket().size();
        basket.getProduct(size);
    }

    @Test
    public void testUpdateProduct() throws Exception {
        when(listOfGoods.getProduct(1)).thenReturn(myList.get(1));
        basket.updateProduct(2, 1);
        assertEquals(myList.get(1), basket.getProduct(2));
    }

    @Test(expected = Exception.class)
    public void testUpdateNonexistentProduct() throws Exception {
        when(listOfGoods.getProduct(1)).thenReturn(myList.get(1));
        int size = basket.getProductsFromBasket().size();
        basket.updateProduct(size, 1);
    }

    @Test(expected = Exception.class)
    public void testUpdateOnNonexistentProduct() throws Exception {
        when(listOfGoods.getProduct(10)).thenThrow(Exception.class);
        basket.updateProduct(1, 10);
    }

    @Test
    public void testAddProduct() throws Exception {
        int sizeBefore = basket.getProductsFromBasket().size();
        when(listOfGoods.getProduct(2)).thenReturn(myList.get(2));
        basket.addProduct(2);
        int sizeAfter = basket.getProductsFromBasket().size();
        assertEquals(sizeBefore + 1, sizeAfter);
    }

    @Test(expected = Exception.class)
    public void testAddNonexistentProduct() throws Exception {
        when(listOfGoods.getProduct(10)).thenThrow(Exception.class);
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
