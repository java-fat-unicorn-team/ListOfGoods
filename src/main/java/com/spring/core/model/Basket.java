package com.spring.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used as basket and contains list of products
 * This class is saved in file and load when products starts if user choose store basket in file
 * @author Katuranau Maksimilyan
 */
public class Basket {
    private final List<Product> basket;

    /**
     * @param basket is list of products from basket
     */
    public Basket(List<Product> basket) {
        this.basket = basket;
    }

    public Basket() {
        basket = new ArrayList<>();
    }

    public List<Product> getBasket() {
        return basket;
    }
}
