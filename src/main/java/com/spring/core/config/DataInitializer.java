package com.spring.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.core.model.ListOfGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

/**
 * This class is used to create object which contains list of products
 * Then you can add product from this list to your basket
 * This class works with json file and gets data from it
 *
 * @author Katuranau Maksimilyan
 */
@Component
public class DataInitializer {

    @Value("${products.file.initializer}")
    private String INITIALIZE_PRODUCTS_FILENAME;
    private ObjectMapper objectMapper;

    /**
     * @param objectMapper is mapper (or, data binder, or codec) which provides functionality for converting between
     *                     Java objects (instances of JDK provided core classes, beans), and matching JSON constructs.
     */
    @Autowired
    public DataInitializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * @return ListOfGoods value is object obtained from a json file
     * @throws IOException is an exception that ObjectMapper throws if it can't read the data
     */
    public ListOfGoods initializeProducts() throws IOException {

        File productsInitializeFile = ResourceUtils.getFile(INITIALIZE_PRODUCTS_FILENAME);

        return objectMapper.readValue(productsInitializeFile, ListOfGoods.class);
    }

    public void setINITIALIZE_PRODUCTS_FILENAME(String INITIALIZE_PRODUCTS_FILENAME) {
        this.INITIALIZE_PRODUCTS_FILENAME = INITIALIZE_PRODUCTS_FILENAME;
    }
}
