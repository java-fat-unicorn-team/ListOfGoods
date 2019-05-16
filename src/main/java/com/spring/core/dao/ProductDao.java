package com.spring.core.dao;

import com.spring.core.model.ListOfGoods;
import com.spring.core.model.Product;

public interface ProductDao {
    ListOfGoods getListOfProducts();

    Product getProduct(int number);

    void updateProduct(Product product, int number);

    void addProduct(Product product);

    void deleteProduct(int number);
}
