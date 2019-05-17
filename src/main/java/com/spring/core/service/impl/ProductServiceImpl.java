package com.spring.core.service.impl;

import com.spring.core.dao.BasketDao;
import com.spring.core.dao.impl.InMemoryBasketDaoImpl;
import com.spring.core.model.Product;
import com.spring.core.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * this class contains InMemoryBasketDaoImpl class which provides all the functionality of the basket
 * @see InMemoryBasketDaoImpl <-- There
 * it is necessary to quickly change place of storing data
 * @author Katuranau Maksimilyan
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final BasketDao basket;

    /**
     *
     * @param basket is an object provides all the functionality of the basket
     */
    @Autowired
    public ProductServiceImpl(BasketDao basket) {
        this.basket = basket;
    }

    public List<Product> getProducts() {

        return basket.getProducts();
    }

    public Product getProduct(int index) {
        return basket.getProduct(index);
    }

    public void updateProduct(int index, Product product) {
        basket.updateProduct(index, product);

    }

    public void addProduct(Product product) {
        basket.addProduct(product);
    }

    public void deleteProduct(int index) {
        basket.deleteProduct(index);
    }

}
