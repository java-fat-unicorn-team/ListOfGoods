package com.spring.core.dao;

import com.spring.core.model.Product;

import java.util.List;

/**
 * Use this interface if you want to create a new way to store list of products
 * @author Katuranau Maksimilyan
 */
public interface ListOfGoodsDao {
    List<Product> getProducts();

    Product getProduct(int index);
}
