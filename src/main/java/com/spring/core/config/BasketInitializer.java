package com.spring.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.core.model.Basket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

/**
 * This class is used when you choose to store basket in file
 * This class works with json file, gets data from it and write to it
 *
 * @author Katuranau Maksimilyan
 */
@Component
public class BasketInitializer {
    @Value("${basket.storage}")
    private String BASKET_STORAGE_FILENAME;
    private ObjectMapper objectMapper;

    /**
     * @param objectMapper is mapper (or, data binder, or codec) which provides functionality for converting between
     *                     Java objects (instances of JDK provided core classes, beans), and matching JSON constructs.
     */
    @Autowired
    public BasketInitializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * this method loads Basket object from file
     * @return Basket value is object which contains list of products from basket
     */
    public Basket initializeBasket() {
        try {
            File productsInitializeFile = ResourceUtils.getFile(BASKET_STORAGE_FILENAME);

            return objectMapper.readValue(productsInitializeFile, Basket.class);
        } catch (Exception e) {
            new File(BASKET_STORAGE_FILENAME);
            return new Basket();
        }
    }

    /**
     * this method is used when you change list of products in basket
     * this method overwrites the list of products in the basket
     * @param basket is a modified object to overwrite file
     * @throws IOException throws when file cannot be overwritten
     */
    public void updateBasket(Basket basket) throws IOException {
        File productsInitializeFile = ResourceUtils.getFile(BASKET_STORAGE_FILENAME);

        objectMapper.writeValue(productsInitializeFile, basket);
    }
}
