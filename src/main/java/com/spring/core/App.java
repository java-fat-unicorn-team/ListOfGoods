package com.spring.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.core.config.AppConfig;
import com.spring.core.dao.BasketDao;
import com.spring.core.dao.impl.InMemoryBasketDaoImpl;
import com.spring.core.model.Product;
import com.spring.core.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.ArrayList;

/**
 * This project is spring console application
 * which provides ability to manage the list of goods
 *
 * @author Katuranau Maksimilyan
 * @version 1.0
 * @since 2019-05-17
 */

public class App {
    /**
     * This method is start point of application
     *
     * @param args is String[] value arguments passed when the application starts
     */
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        //Get object which contains list of goods and provides ability to manage them
        ProductService productService = context.getBean(ProductService.class);

        System.out.println("\nOriginal list of products:");
        productService.getProducts().forEach(System.out::println);

        System.out.println("\nSecond product is updated:");
        //Update product number 2
        productService.updateProduct(2, new Product("chocolate", 7, 80));
        productService.getProducts().forEach(System.out::println);

        System.out.println("\nSecond product is deleted:");
        //Delete product number 2
        productService.deleteProduct(2);
        productService.getProducts().forEach(System.out::println);

        System.out.println("\nThird product:");
        System.out.println(productService.getProduct(3));

    }
}
