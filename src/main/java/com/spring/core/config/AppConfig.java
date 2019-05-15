package com.spring.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.core.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;
import java.util.List;

@ComponentScan("com.spring.core")
@Configuration
public class AppConfig {
    @Bean("defaultProduct")
    @Scope("prototype")
    public Product getProduct() {
        return new Product();
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    @Bean("defaultList")
    @Autowired
    public List<Product> getListOfGoods(DataInitializer dataInitializer) throws IOException {
        return dataInitializer.initializeProducts();
    }

    @Bean
    @Autowired
    public DataInitializer getDataInitializer(ObjectMapper objectMapper) {
        return new DataInitializer(objectMapper);
    }

    @Bean(name = "productPhone")
    public Product getProductTest() {
        return new Product("phone", 350, 320);
    }
}
