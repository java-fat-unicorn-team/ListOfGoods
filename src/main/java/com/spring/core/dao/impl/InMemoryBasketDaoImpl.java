package com.spring.core.dao.impl;

import com.spring.core.dao.BasketDao;
import com.spring.core.model.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements BasketDao interface which provides methods to manage products basket
 * @see BasketDao <-- There
 * @author Katuranau Maksimilyan
 */
public class InMemoryBasketDaoImpl implements BasketDao {

    private final List<Product> basket;

    public InMemoryBasketDaoImpl() {
        basket = new ArrayList<>();
    }

    @Override
    public List<Product> getProducts() {
        return basket;
    }

    @Override
    public Product getProduct(int index) {
        return basket.get(index);
    }

    @Override
    public void updateProduct(int index, Product product) {
        basket.set(index, product);
    }

    @Override
    public void addProduct(Product product) {
        basket.add(product);
    }

    @Override
    public void deleteProduct(int index) {
        basket.remove(index);
    }
}
