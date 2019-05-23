package com.spring.core.dao.impl;

import com.spring.core.config.BasketInitializer;
import com.spring.core.dao.BasketDao;
import com.spring.core.dao.ListOfGoodsDao;
import com.spring.core.model.Basket;
import com.spring.core.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This class provides methods to manage products basket
 * products are stored in file
 * @author Katuranau Maksimilyan
 * {@see BasketDao}
 */
@Component
@Profile("fromFile")
public class InFileBasketDao implements BasketDao {

    private Basket basket;
    private final ListOfGoodsDao listOfProducts;
    private final BasketInitializer basketInitializer;

    /**
     * @param listOfProducts is a class which contains list of all products and loads from file when project starts
     * @param basketInitializer is  is a class that receives and saves to the file a list of products from the file
     */
    @Autowired
    public InFileBasketDao(ListOfGoodsDao listOfProducts, BasketInitializer basketInitializer) {
        this.listOfProducts = listOfProducts;
        this.basketInitializer = basketInitializer;
        basket = basketInitializer.initializeBasket();
    }

    @Override
    public List<Product> getAllProducts() {
        return listOfProducts.getProducts();
    }

    @Override
    public List<Product> getProductsFromBasket() {
        return basket.getBasket();
    }

    @Override
    public Product getProduct(int index) {
        return basket.getBasket().get(index);
    }

    @Override
    public void updateProduct(int index, int indexInBasket) throws Exception {
        basket.getBasket().set(index, listOfProducts.getProduct(indexInBasket));
        basketInitializer.updateBasket(basket);
    }

    @Override
    public void addProduct(int indexInBasket) throws Exception {
        basket.getBasket().add(listOfProducts.getProduct(indexInBasket));
        basketInitializer.updateBasket(basket);

    }

    @Override
    public void deleteProduct(int index) throws Exception {
        basket.getBasket().remove(index);
        basketInitializer.updateBasket(basket);
    }
}
