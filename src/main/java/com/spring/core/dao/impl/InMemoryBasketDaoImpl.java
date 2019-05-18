package com.spring.core.dao.impl;

import com.spring.core.dao.BasketDao;
import com.spring.core.dao.ListOfGoodsDao;
import com.spring.core.model.ListOfGoods;
import com.spring.core.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * This class implements BasketDao interface which provides methods to manage products basket
 * @see BasketDao <-- There
 * @author Katuranau Maksimilyan
 */
@Component
public class InMemoryBasketDaoImpl implements BasketDao {

    private final List<Product> basket;
    private final ListOfGoodsDao listOfProducts;


    @Autowired
    public InMemoryBasketDaoImpl(ListOfGoodsDao listOfProducts) {
        basket = new ArrayList<>();
        this.listOfProducts = listOfProducts;
    }

    @Override
    public List<Product> getProductsFromBasket() {
        return basket;
    }

    @Override
    public List<Product> getAllProducts() {
        return listOfProducts.getProducts();
    }

    @Override
    public Product getProduct(int index) throws IndexOutOfBoundsException {
        return basket.get(index);
    }

    @Override
    public void updateProduct(int index, int indexInBasket) {
        basket.set(index, listOfProducts.getProduct(indexInBasket));
    }

    @Override
    public void addProduct(int indexInBasket) {
        basket.add(listOfProducts.getProduct(indexInBasket));
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
