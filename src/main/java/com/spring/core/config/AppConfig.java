package com.spring.core.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.core.console_input.ConsoleInputValidator;
import com.spring.core.model.ListOfGoods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

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
    public InputStream getInputStream() {
        return System.in;
    }

    @Bean
    public ConsoleInputValidator getConsoleInputValidator(InputStream inputStream) {
        return new ConsoleInputValidator(inputStream);
    }
}
