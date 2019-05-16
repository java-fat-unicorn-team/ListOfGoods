package com.spring.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.core.model.ListOfGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;

@Component
public class DataInitializer {

    private static final String INITIALIZE_PRODUCTS_FILENAME = "classpath:products-initializer.json";
    private ObjectMapper objectMapper;

    @Autowired
    public DataInitializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public ListOfGoods initializeProducts() throws IOException {

        File productsInitializeFile = ResourceUtils.getFile(INITIALIZE_PRODUCTS_FILENAME);

        return objectMapper.readValue(productsInitializeFile, ListOfGoods.class);
    }
}
