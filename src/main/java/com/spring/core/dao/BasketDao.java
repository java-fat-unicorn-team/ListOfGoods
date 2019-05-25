package com.spring.core.dao;

import com.spring.core.model.Product;

import java.util.List;

/**
 * Use this interface provides methods to manage basket.
 *
 * @author Katuranau Maksimilyan
 */
public interface BasketDao {
    /**
     * @return List<Product> value is list of products
     */
    List<Product> getAllProducts();

    /**
     * @return List<Product> value is list of products from basket
     */
    List<Product> getProductsFromBasket();

    /**
     * @param index is index of product to be obtained
     * @return Product value is obtained product
     */
    Product getProduct(int index);

    /**
     * @param index         is index of product from basket to be updated
     * @param indexInBasket is index of product to be added
     * @throws Exception wrong index
     */
    void updateProduct(int index, int indexInBasket) throws Exception;

    /**
     * @param indexInBasket is index of product to be added
     * @throws Exception wrong index.
     */
    void addProduct(int indexInBasket) throws Exception;

    /**
     * @param index product's index from basket to be deleted
     * @throws Exception wrong index
     */
    void deleteProduct(int index) throws Exception;
}
