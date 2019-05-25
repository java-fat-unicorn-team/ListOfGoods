package com.spring.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used as basket and contains list of products.
 * This class is saved in file if user choose store basket in file.
 *
 * @author Katuranau Maksimilyan
 */
public class Basket {
    /**
     * List of basket.
     */
    private List<Product> basket;

    /**
     * initialize basket with empty.
     */
    public Basket() {
        basket = new ArrayList<>();
    }

    /**
     * returns list of basket.
     *
     * @return list of basket
     */
    public List<Product> getBasket() {
        return basket;
    }
}
