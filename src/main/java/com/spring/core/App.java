package com.spring.core;

import com.spring.core.config.AppConfig;
import com.spring.core.model.Product;
import com.spring.core.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        //Get object which contains list of goods and provides ability to manage them
        ProductService productService = context.getBean(ProductService.class);

        System.out.println("\nOriginal list of products:");
        productService.getListOfProducts().getProducts().forEach(System.out::println);

        System.out.println("\nSecond product is updated:");
        //Update product number 2
        productService.updateProduct(new Product("chocolate", 7, 80), 2);
        productService.getListOfProducts().getProducts().forEach(System.out::println);

        System.out.println("\nSecond product is deleted:");
        //Delete product number 2
        productService.deleteProduct(2);
        productService.getListOfProducts().getProducts().forEach(System.out::println);

        System.out.println("\nThird product:");
        System.out.println(productService.getProduct(3));

    }
}
