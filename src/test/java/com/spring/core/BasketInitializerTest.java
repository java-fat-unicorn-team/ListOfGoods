package com.spring.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.core.config.BasketInitializer;
import com.spring.core.model.Basket;
import com.spring.core.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class BasketInitializerTest {
    private String BASKET_FILENAME = "classpath:basket-storage.json";
    private BasketInitializer basketInitializer;
    private Basket basket;
    private Field field;

    @Before
    public void initializeBasket() {
        basket = new Basket();
        basket.getBasket().add(new Product("phone", new BigDecimal(790.22), 340));
        basket.getBasket().add(new Product("pan", new BigDecimal(9.12), 15));
        basket.getBasket().add(new Product("computer", new BigDecimal(3200.00), 2100));
        basketInitializer = new BasketInitializer(new ObjectMapper());
        field = ReflectionUtils.findField(BasketInitializer.class, "BASKET_STORAGE_FILENAME");
        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field, basketInitializer, BASKET_FILENAME);
    }

    @Test
    public void testUpdateBasket() throws Exception {
        basketInitializer.updateBasket(basket);
        Basket basket2 = basketInitializer.initializeBasket();
        assertEquals(basket.getBasket().get(0).getWeight(), basket2.getBasket().get(0).getWeight());
        assertEquals(basket.getBasket().get(1).getPrice(), basket2.getBasket().get(1).getPrice());
        assertEquals(basket.getBasket().get(2).getName(), basket2.getBasket().get(2).getName());
    }

    @Test
    public void testGetBasket() throws Exception {
        basketInitializer.updateBasket(basket);
        Basket basket2 = basketInitializer.initializeBasket();
        assertEquals(basket.getBasket().get(0).getWeight(), basket2.getBasket().get(0).getWeight());
        assertEquals(basket.getBasket().get(1).getPrice(), basket2.getBasket().get(1).getPrice());
        assertEquals(basket.getBasket().get(2).getName(), basket2.getBasket().get(2).getName());
    }

    @Test
    public void testGetBasketNull() throws Exception {
        basketInitializer.updateBasket(null);
        assertNull(basketInitializer.initializeBasket());
    }

    @Test
    public void testGetBasketWrongFile() {
        ReflectionUtils.setField(field, basketInitializer, "classpath:products-initializer.json");
        Basket basket2 = basketInitializer.initializeBasket();
        assertEquals(0, basket2.getBasket().size());
    }
}
