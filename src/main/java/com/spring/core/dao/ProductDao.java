package com.spring.core.dao;

import com.spring.core.model.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getListOfProducts();

    Product getProduct(int number);

    void updateProduct(Product product, int number);

    void addProduct(Product product);

    void deleteProduct(int number);
}
