package com.spring.core.service;

import com.spring.core.model.ListOfGoods;
import com.spring.core.model.Product;

public interface ProductService {

    ListOfGoods getListOfProducts();

    Product getProduct(int number);

    void updateProduct(Product product, int number);

    void addProduct(Product product);

    void deleteProduct(int number);

}
