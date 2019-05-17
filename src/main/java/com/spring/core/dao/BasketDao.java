package com.spring.core.dao;

import com.spring.core.model.Product;

import java.util.List;

/**
 * this interface provides methods to manage products basket
 * @author Katuranau Maksimilyan
 */
public interface BasketDao {
    /**
     *
     * @return List<Product> value is list of products
     */
    List<Product> getProducts();

    /**
     *
     * @param index is index of product to be obtained
     * @return Product value is obtained product
     */
    Product getProduct(int index);

    /**
     *
     * @param index is index of product to be updated
     * @param product is new project you provide to replace old
     */
    void updateProduct(int index, Product product);

    /**
     *
     * @param product is product to be added to basket
     */
    void addProduct(Product product);

    /**
     *
     * @param index product's index to be added to basket deleted
     */
    void deleteProduct(int index);
}
