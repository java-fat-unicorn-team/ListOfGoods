package com.spring.core.dao.impl;

import com.spring.core.dao.BasketDao;
import com.spring.core.dao.ListOfGoodsDao;
import com.spring.core.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * This class provides methods to manage products basket
 * products are stored in memory
 * @author Katuranau Maksimilyan
 * {@see BasketDao}
 */
@Component
@Profile("dev")
public class InMemoryBasketDao implements BasketDao {
    private final List<Product> basket;
    private final ListOfGoodsDao listOfProducts;


    @Autowired
    public InMemoryBasketDao(ListOfGoodsDao listOfProducts) {
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
    public Product getProduct(int index) throws Exception {
        return basket.get(index);
    }

    @Override
    public void updateProduct(int index, int indexInBasket) throws Exception {
        basket.set(index, listOfProducts.getProduct(indexInBasket));
    }

    @Override
    public void addProduct(int indexInBasket) throws Exception {
        basket.add(listOfProducts.getProduct(indexInBasket));
    }

    @Override
    public void deleteProduct(int index) throws Exception {
        basket.remove(index);
    }
}
