package com.spring.core;

import com.spring.core.dao.BasketDao;
import com.spring.core.model.Product;
import com.spring.core.service.impl.BasketServiceImpl;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BasketServiceTest {

    @Mock
    private BasketDao basket;
    @InjectMocks
    private BasketServiceImpl basketService;

    private static List<Product> listOfProducts;
    private static List<Product> testListOfProduct;

    @BeforeClass
    public static void initialProductService() {
        listOfProducts = new ArrayList<>() {{
            add(new Product("phone", new BigDecimal(790.22), 340));
            add(new Product("pan", new BigDecimal(9.12), 15));
            add(new Product("computer", new BigDecimal(3200.00), 2100));
        }};
        testListOfProduct = new ArrayList<>() {{
            add(new Product("phone", new BigDecimal(790.22), 340));
            add(new Product("pan", new BigDecimal(9.12), 15));
            add(new Product("computer", new BigDecimal(3200.00), 2100));
        }};
    }

    @After
    public void resetMocks() {
        Mockito.reset(basket);
    }

    @Test
    public void testGetProductsFromBasket() {
        when(basket.getProductsFromBasket()).thenReturn(testListOfProduct);
        assertEquals(listOfProducts.get(0).getWeight(), basketService.getProductsFromBasket().get(0).getWeight());
        assertEquals(listOfProducts.get(1).getPrice(), basketService.getProductsFromBasket().get(1).getPrice());
        assertEquals(listOfProducts.get(2).getName(), basketService.getProductsFromBasket().get(2).getName());
    }

    @Test
    public void testGetAllProducts() {
        when(basket.getAllProducts()).thenReturn(testListOfProduct);
        assertEquals(listOfProducts.get(0).getWeight(), basketService.getAllProducts().get(0).getWeight());
        assertEquals(listOfProducts.get(1).getPrice(), basketService.getAllProducts().get(1).getPrice());
        assertEquals(listOfProducts.get(2).getName(), basketService.getAllProducts().get(2).getName());
    }

    @Test
    public void testGetProduct() throws Exception {
        when(basket.getProduct(1)).thenReturn(testListOfProduct.get(1));
        assertEquals(listOfProducts.get(1).getPrice(), basketService.getProduct(1).getPrice());
        assertEquals(listOfProducts.get(1).getWeight(), basketService.getProduct(1).getWeight());
    }

    @Test(expected = Exception.class)
    public void testGetNonexistentProduct() throws Exception {
        when(basket.getProduct(20)).thenThrow(Exception.class);
        basketService.getProduct(20);
    }

    @Test(expected = Exception.class)
    public void testGetProductFail() throws Exception {
        when(basket.getProduct(-2)).thenThrow(Exception.class);
        basketService.getProduct(-2);
    }

    @Test
    public void testUpdateProduct() throws Exception {
        basketService.updateProduct(2, 1);
        verify(basket).updateProduct(2, 1);
    }

    @Test
    public void testAddProduct() throws Exception {
        basketService.addProduct(2);
        verify(basket).addProduct(2);
    }

    @Test
    public void testDeleteProduct() throws Exception {
        basketService.deleteProduct(3);
        verify(basket).deleteProduct(3);
    }
}
