package com.spring.core.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains list of products
 * This class is created by dataInitializer when project starts
 * @author Katuranau Maksimilyan
 */
public class ListOfGoods {
    private final List<Product> listOfGoods;

    /**
     * @param listOfGoods is list of products
     */
    public ListOfGoods(List<Product> listOfGoods) {
        this.listOfGoods = listOfGoods;
    }

    public ListOfGoods() {
        listOfGoods = new ArrayList<>();
    }

    public List<Product> getListOfGoods() {
        return listOfGoods;
    }
}
