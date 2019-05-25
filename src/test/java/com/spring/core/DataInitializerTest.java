package com.spring.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.core.config.DataInitializer;
import com.spring.core.model.ListOfGoods;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ReflectionUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DataInitializerTest {
    private String PRODUCTS_FILENAME = "products-initializer.json";
    DataInitializer dataInitializer;
    ListOfGoods listOfGoods;
    ListOfGoods listOfGoodsTest;

    @Before
    public void initialDataInitializer() {
        dataInitializer = new DataInitializer(new ObjectMapper());
        Field field = ReflectionUtils.findField(DataInitializer.class, "initializeProductsFilename");
        ReflectionUtils.makeAccessible(field);
        ReflectionUtils.setField(field, dataInitializer, PRODUCTS_FILENAME);
        try {
            File productsInitializeFile = new ClassPathResource(PRODUCTS_FILENAME).getFile();
            listOfGoods = new ObjectMapper().readValue(productsInitializeFile, ListOfGoods.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInitializeProducts() {
        try {
            listOfGoodsTest = dataInitializer.initializeProducts();
        } catch (IOException ex) {
            fail("product initialization failed");
        }
        int expectedSize = listOfGoods.getListOfGoods().size();
        int actualSize = listOfGoodsTest.getListOfGoods().size();
        assertEquals(expectedSize, actualSize);
        assertEquals(listOfGoods.getListOfGoods().get(0).getName(), listOfGoodsTest.getListOfGoods().get(0).getName());
        assertEquals(listOfGoods.getListOfGoods().get(1).getPrice(), listOfGoodsTest.getListOfGoods().get(1).getPrice());
        assertEquals(listOfGoods.getListOfGoods().get(expectedSize - 1).getWeight(),
                listOfGoodsTest.getListOfGoods().get(actualSize - 1).getWeight(), 0.001);

    }
}