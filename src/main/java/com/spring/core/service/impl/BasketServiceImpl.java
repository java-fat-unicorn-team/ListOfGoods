package com.spring.core.service.impl;

import com.spring.core.dao.BasketDao;
import com.spring.core.model.Product;
import com.spring.core.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class provides all the functionality
 * Then it is used to make user interface
 * This class manages data is received from memory
 * @author Katuranau Maksimilyan
 * {@see InMemoryBasketDao}
 */
@Service
public class BasketServiceImpl implements BasketService {

    private final BasketDao basket;

    /**
     * @param basket is an object provides all the functionality of the basket
     */
    @Autowired
    public BasketServiceImpl(BasketDao basket) {
        this.basket = basket;
    }

    public List<Product> getAllProducts() {

        return basket.getAllProducts();
    }

    public List<Product> getProductsFromBasket() {

        return basket.getProductsFromBasket();
    }

    public Product getProduct(int index) throws IndexOutOfBoundsException {
        return basket.getProduct(index);
    }

    public void updateProduct(int index,  int indexInBasket) throws IndexOutOfBoundsException {
        basket.updateProduct(index, indexInBasket);

    }

    public void addProduct(int indexInBasket) {
        basket.addProduct(indexInBasket);
    }

    public void deleteProduct(int index) throws IndexOutOfBoundsException {
        basket.deleteProduct(index);
    }

}
