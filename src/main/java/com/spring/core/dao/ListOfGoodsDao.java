package com.spring.core.dao;

import com.spring.core.model.Product;

import java.util.List;

/**
 * Provides methods to get products from list.
 * Use this interface if you want to create a new way to store list of products
 *
 * @author Katuranau Maksimilyan
 */
public interface ListOfGoodsDao {
    /**
     * @return List of all products
     */
    List<Product> getProducts();

    /**
     * @param index is product's index you want to get
     * @return Product is a product with index you entered
     * @throws Exception is threw when there is no product with such index
     */
    Product getProduct(int index) throws Exception;
}
