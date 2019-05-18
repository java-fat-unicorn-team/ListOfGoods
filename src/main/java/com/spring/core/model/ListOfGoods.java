package com.spring.core.model;

import java.util.ArrayList;
import java.util.List;

public class ListOfGoods {
    private final List<Product> listOfGoods;

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
