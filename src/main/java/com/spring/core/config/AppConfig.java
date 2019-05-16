package com.spring.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.core.model.ListOfGoods;
import com.spring.core.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;

@ComponentScan("com.spring.core")
@Configuration
public class AppConfig {
    @Bean
    @Scope("prototype")
    public Product getProduct() {
        return new Product();
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    @Autowired
    public ListOfGoods getListOfGoods(DataInitializer dataInitializer) throws IOException {
        return dataInitializer.initializeProducts();
    }

    @Bean
    @Autowired
    public DataInitializer getDataInitializer(ObjectMapper objectMapper) {
        return new DataInitializer(objectMapper);
    }

}
