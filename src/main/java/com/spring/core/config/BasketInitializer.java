package com.spring.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.core.model.Basket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * This class is used when you choose to store basketStorageFilename in file.
 * This class works with json file, gets data from it and write to it
 *
 * @author Katuranau Maksimilyan
 */
@Component
public class BasketInitializer {
    /**
     * basket storage file name.
     */
    @Value("${basket.storage}")
    private String basketStorageFilename;
    /**
     * object mapper to work with json file.
     */
    private ObjectMapper objectMapper;

    /**
     * @param pObjectMapper is mapper to converting between Java object and JSON
     */
    @Autowired
    public BasketInitializer(final ObjectMapper pObjectMapper) {
        this.objectMapper = pObjectMapper;
    }

    /**
     * this method loads Basket object from file.
     *
     * @return object which contains list of products from basket
     */
    public final Basket initializeBasket() {
        try {
            File productsInitializeFile
                    = new ClassPathResource(basketStorageFilename).getFile();
            return objectMapper.readValue(productsInitializeFile, Basket.class);
        } catch (Exception e) {
            new File(basketStorageFilename);
            return new Basket();
        }
    }

    /**
     * this method is used when you change list of products.
     * this method overwrites the list of products in the basketStorageFilename.
     *
     * @param basket is a modified object to overwrite file
     * @throws IOException throws when file cannot be overwritten
     */
    public void updateBasket(final Basket basket) throws IOException {
        File productsInitializeFile
                = new ClassPathResource(basketStorageFilename).getFile();
        objectMapper.writeValue(productsInitializeFile, basket);
    }
}
