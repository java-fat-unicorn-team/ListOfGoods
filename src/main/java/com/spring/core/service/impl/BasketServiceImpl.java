package com.spring.core.service.impl;

import com.spring.core.dao.BasketDao;
import com.spring.core.model.Product;
import com.spring.core.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class provides methods to manage basket
 * This class can manage a basket that is stored in memory or in a file
 * @author Katuranau Maksimilyan
 * {@see InMemoryBasketDao}
 */
@Service
public class BasketServiceImpl implements BasketService {

    private final BasketDao basket;

    /**
     * @param basket is an object provides methods to manage basket
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

    public Product getProduct(int index) throws Exception {
        return basket.getProduct(index);
    }

    public void updateProduct(int index,  int indexInBasket) throws Exception {
        basket.updateProduct(index, indexInBasket);

    }

    public void addProduct(int indexInBasket) throws Exception {
        basket.addProduct(indexInBasket);
    }

    public void deleteProduct(int index) throws Exception {
        basket.deleteProduct(index);
    }

}
