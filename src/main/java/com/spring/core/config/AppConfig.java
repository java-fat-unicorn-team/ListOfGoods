package com.spring.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.core.dao.BasketDao;
import com.spring.core.model.ListOfGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

@ComponentScan("com.spring.core")
@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

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
    public PrintStream getPrintStream() {
        return new PrintStream(System.out);
    }

    @Bean
    public Scanner getScanner() {
        return new Scanner(System.in);
    }
}
