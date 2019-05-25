package com.spring.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains list of products.
 * This class is created by dataInitializer when project starts
 *
 * @author Katuranau Maksimilyan
 */
public class ListOfGoods {
    /**
     * list of products.
     */
    private List<Product> listOfGoods;

    /**
     * default constructor.
     */
    public ListOfGoods() {
        listOfGoods = new ArrayList<>();
    }

    /**
     * return list of products.
     *
     * @return list of products
     */
    public List<Product> getListOfGoods() {
        return listOfGoods;
    }
}
