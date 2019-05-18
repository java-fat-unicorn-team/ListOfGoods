package com.spring.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.core.dao.BasketDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

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

    @Bean
    public PrintStream getPrintStream() {
        return new PrintStream(System.out);
    }

    @Bean
    public Scanner getScanner() {
        return new Scanner(System.in);
    }
}
