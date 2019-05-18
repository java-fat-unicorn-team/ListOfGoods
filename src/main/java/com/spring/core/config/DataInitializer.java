package com.spring.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.core.dao.impl.InMemoryBasketDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

/**
 * This class works with json file and gets data from it
 *
 * @author Katuranau Maksimilyan
 */
@Component
public class DataInitializer {

    //@Value("products.file.initializer")
    private String INITIALIZE_PRODUCTS_FILENAME = "classpath:products-initializer.json";
    private ObjectMapper objectMapper;

    /**
     *
     * @param objectMapper is mapper (or, data binder, or codec) which provides functionality for converting between Java objects (instances of JDK provided core classes, beans), and matching JSON constructs.
     */
    @Autowired
    public DataInitializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     *
     * @return BasketDao value is object obtained from a json file
     * @throws IOException
     */
    public InMemoryBasketDaoImpl initializeProducts() throws IOException {

        File productsInitializeFile = ResourceUtils.getFile(INITIALIZE_PRODUCTS_FILENAME);

        return objectMapper.readValue(productsInitializeFile, InMemoryBasketDaoImpl.class);
    }
}
