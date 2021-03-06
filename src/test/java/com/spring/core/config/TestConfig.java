package com.spring.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.core.console_input.ConsoleInputValidator;
import com.spring.core.model.Basket;
import com.spring.core.model.ListOfGoods;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@ComponentScan("com.spring.core")
@Configuration
@PropertySource("classpath:application.properties")
public class TestConfig {

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
    public Basket getBasket(final BasketInitializer basketInitializer) {
        return basketInitializer.initializeBasket();
    }

    @Bean
    public PrintStream getPrintStream() {
        return Mockito.mock(PrintStream.class);
    }

    @Bean
    public InputStream getInputStream() {
        return System.in;
    }

    @Bean
    @Autowired
    public Scanner getScanner(final InputStream inputStream) {
        return new Scanner(inputStream);
    }

    @Bean
    public ConsoleInputValidator getConsoleInputValidator() {
        return Mockito.mock(ConsoleInputValidator.class);
    }
}