package com.spring.core.dao.impl;

import com.spring.core.dao.BasketDao;
import com.spring.core.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public InMemoryBasketDaoImpl(List<Product> basket) {
        this.basket = basket;
    }

    @Override
    public List<Product> getProducts() {
        return basket;
    }

    @Override
    public Product getProduct(int index) throws IndexOutOfBoundsException {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InMemoryBasketDaoImpl that = (InMemoryBasketDaoImpl) o;
        return Objects.equals(basket, that.basket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(basket);
    }
}
