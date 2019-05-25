package com.spring.core.dao.impl;

import com.spring.core.dao.BasketDao;
import com.spring.core.dao.ListOfGoodsDao;
import com.spring.core.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides methods to manage products basket.
 * products are stored in memory *
 *
 * @author Katuranau Maksimilyan
 * {@see BasketDao}
 */
@Repository
@Profile("dev")
public class InMemoryBasketDao implements BasketDao {
    /**
     * list of products in basket.
     */
    private final List<Product> basket;
    /**
     * list of all products.
     */
    private final ListOfGoodsDao listOfProducts;

    /**
     * @param pListOfProducts object which contains list of all products
     */
    @Autowired
    public InMemoryBasketDao(final ListOfGoodsDao pListOfProducts) {
        basket = new ArrayList<>();
        listOfProducts = pListOfProducts;
    }

    /**
     * @return list of products from basket
     */
    @Override
    public List<Product> getProductsFromBasket() {
        return basket;
    }

    /**
     * @return list of all products
     */
    @Override
    public List<Product> getAllProducts() {
        return listOfProducts.getProducts();
    }

    /**
     * @param index is index of product to be obtained
     * @return product with entered index
     * @throws Exception
     */
    @Override
    public Product getProduct(final int index) {
        return basket.get(index);
    }

    /**
     * @param index         is index of product from basket to be updated
     * @param indexInBasket is index of product to be added
     * @throws Exception
     */
    @Override
    public void updateProduct(final int index,
                              final int indexInBasket) throws Exception {
        basket.set(index, listOfProducts.getProduct(indexInBasket));
    }

    /**
     * @param indexInBasket is index of product to be added
     * @throws Exception
     */
    @Override
    public void addProduct(final int indexInBasket) throws Exception {
        basket.add(listOfProducts.getProduct(indexInBasket));
    }

    /**
     * @param index product's index from basket to be deleted
     * @throws Exception
     */
    @Override
    public void deleteProduct(final int index) throws Exception {
        basket.remove(index);
    }
}
