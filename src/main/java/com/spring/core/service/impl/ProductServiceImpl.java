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
 *
 * @author Katuranau Maksimilyan
 * @see InMemoryBasketDaoImpl <-- There
 * it is necessary to quickly change place of storing data
 */
@Service
public class ProductServiceImpl implements ProductService {

    private final BasketDao basket;

    /**
     * @param basket is an object provides all the functionality of the basket
     */
    @Autowired
    public ProductServiceImpl(BasketDao basket) {
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
