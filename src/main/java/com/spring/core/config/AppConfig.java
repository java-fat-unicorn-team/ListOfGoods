package com.spring.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.core.dao.BasketDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@ComponentScan("com.spring.core")
@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    @Autowired
    public BasketDao getBasket(DataInitializer dataInitializer) throws IOException {
        return dataInitializer.initializeProducts();
    }
}
