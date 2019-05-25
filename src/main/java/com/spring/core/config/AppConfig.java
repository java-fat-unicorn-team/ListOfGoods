package com.spring.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.core.console_input.ConsoleInputValidator;
import com.spring.core.model.Basket;
import com.spring.core.model.ListOfGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Configuration class which provides beans to create classes.
 */
@ComponentScan("com.spring.core")
@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    /**
     * create object mapper.
     * @return object mapper
     */
    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    /**
     * create ListOfGoods class.
     * @param dataInitializer class to work with JSON file
     * @return list of goods
     * @throws IOException if reading from file failed
     */
    @Bean
    @Autowired
    public ListOfGoods getListOfGoods(
            final DataInitializer dataInitializer) throws IOException {
        return dataInitializer.initializeProducts();
    }

    /**
     * create Basket class.
     * @param basketInitializer class to work with JSON file
     * @return basket
     */
    @Bean
    @Autowired
    public Basket getBasket(final BasketInitializer basketInitializer) {
        return basketInitializer.initializeBasket();
    }

    /**
     * create PrintStream class.
     * @return print stream
     */
    @Bean
    public PrintStream getPrintStream() {
        return new PrintStream(System.out);
    }

    /**
     * create InputStream class.
     * @return input stream
     */
    @Bean
    public InputStream getInputStream() {
        return System.in;
    }

    /**
     * create Scanner class.
     * @param inputStream input stream
     * @return scanner
     */
    @Bean
    @Autowired
    public Scanner getScanner(final InputStream inputStream) {
        return new Scanner(inputStream);
    }
    /**
     * create ConsoleInputValidator class.
     * @param scanner class to get data from console
     * @return class receiving data from console
     */
    @Bean
    public ConsoleInputValidator getConsoleInputValidator(
            final Scanner scanner) {
        return new ConsoleInputValidator(scanner);
    }
}
