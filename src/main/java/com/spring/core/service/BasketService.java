package com.spring.core.service;

import com.spring.core.model.Product;

import java.util.List;

/**
 * Use this interface if you want to create a new service
 * @author Katuranau Maksimilyan
 */
public interface BasketService {

    /**
     *
     * @return List<Product> value is list of products
     */
    List<Product> getAllProducts();

    /**
     *
     * @return List<Product> value is list of products from basket
     */
    List<Product> getProductsFromBasket();

    /**
     *
     * @param index is index of product to be obtained
     * @return Product value is obtained product
     */
    Product getProduct(int index);

    /**
     *
     * @param index is index of product to be updated
     * @param indexInBasket is index of product to be added
     */
    void updateProduct(int index, int indexInBasket);

    /**
     *
     * @param indexInBasket is index of product to be added to basket
     */
    void addProduct(int indexInBasket);

    /**
     *
     * @param index product's index from basket to be deleted
     */
    void deleteProduct(int index);
}