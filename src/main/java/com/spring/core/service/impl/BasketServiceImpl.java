package com.spring.core.service.impl;

import com.spring.core.dao.BasketDao;
import com.spring.core.model.Product;
import com.spring.core.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class provides methods to manage basket.
 *
 * @author Katuranau Maksimilyan
 * {@see InMemoryBasketDao}
 */
@Service
public class BasketServiceImpl implements BasketService {

    /**
     * class which contains list of products and basket.
     */
    private final BasketDao basket;

    /**
     * @param pBasket is an object provides methods to manage basket
     */
    @Autowired
    public BasketServiceImpl(final BasketDao pBasket) {
        this.basket = pBasket;
    }

    /**
     * @return List<Product> value is list of products
     */
    public final List<Product> getAllProducts() {

        return basket.getAllProducts();
    }

    /**
     * @return List<Product> value is list of products from basket
     */
    public final List<Product> getProductsFromBasket() {

        return basket.getProductsFromBasket();
    }

    /**
     * @param index is index of product to be obtained
     * @return Product value is obtained product
     */
    public final Product getProduct(final int index) {
        return basket.getProduct(index);
    }

    /**
     * @param index         is index of product from basket to be updated
     * @param indexInBasket is index of product to be added
     * @throws Exception wrong index
     */
    public final void updateProduct(final int index,
                              final int indexInBasket) throws Exception {
        basket.updateProduct(index, indexInBasket);
    }

    /**
     * @param indexInBasket is index of product to be added
     * @throws Exception wrong index
     */
    public final void addProduct(final int indexInBasket) throws Exception {
        basket.addProduct(indexInBasket);
    }

    /**
     * @param index product's index from basket to be deleted
     * @throws Exception wrong index
     */
    public final void deleteProduct(final int index) throws Exception {
        basket.deleteProduct(index);
    }
}
