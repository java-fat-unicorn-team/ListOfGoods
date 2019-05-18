package com.spring.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.core.config.DataInitializer;
import com.spring.core.dao.impl.InMemoryBasketDaoImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DataInitializerTest {
    private String INITIALIZE_PRODUCTS_FILENAME = "classpath:products-initializer.json";
    DataInitializer dataInitializer;
    InMemoryBasketDaoImpl basket;

    @Before
    public void initialDataInitializer() {
        dataInitializer = new DataInitializer(new ObjectMapper());
        try {
            File productsInitializeFile = ResourceUtils.getFile(INITIALIZE_PRODUCTS_FILENAME);
            basket = new ObjectMapper().readValue(productsInitializeFile, InMemoryBasketDaoImpl.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInitializeProducts() {
        try {
            assertEquals(dataInitializer.initializeProducts(), basket);
        } catch (IOException ex) {
            fail("product initialization failed");
        }
    }
}