package com.spring.core;

import com.spring.core.config.AppConfig;
import com.spring.core.model.Product;
import com.spring.core.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class App {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        //Getting empty list of goods
        ProductService productService = context.getBean(ProductService.class);
        List<Product> products = productService.getListOfProducts();

        products.forEach(System.out::println);


    }
}
