package com.spring.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.core.model.ListOfGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

/**
 * This class is used to create object which contains list of products.
 * Then you can add product from this list to your basket.
 * This class works with json file and gets data from it.
 *
 * @author Katuranau Maksimilyan
 */
@Component
public class DataInitializer {

    /**
     * basket storage file name.
     */
    @Value("${products.file.initializer}")
    private String initializeProductsFilename;
    /**
     * object mapper to work with json file.
     */
    private ObjectMapper objectMapper;

    /**
     * @param pObjectMapper is mapper to converting between Java object and JSON
     */
    @Autowired
    public DataInitializer(final ObjectMapper pObjectMapper) {
        this.objectMapper = pObjectMapper;
    }

    /**
     * @return ListOfGoods value is object obtained from a json file.
     * @throws IOException throws if it can't read the data.
     */
    public final ListOfGoods initializeProducts() throws IOException {
        File productsInitializeFile
                = new ClassPathResource(initializeProductsFilename).getFile();
        return objectMapper.readValue(productsInitializeFile,
                ListOfGoods.class);
    }
}
