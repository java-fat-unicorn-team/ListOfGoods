package com.spring.core.model;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

public class ListOfGoods {
    private ArrayList<Product> products;

    public ListOfGoods() {
        products = new ArrayList<>();
    }

    public ListOfGoods(ArrayList<Product> list) {
        products = list;
    }

    public ArrayList<Product> getListOfProdacts() {

        return products;
    }

    public Product getProduct(int number) {
        return products.get(--number);
    }

    public void updateProduct(Product product, int number) {
        products.remove(--number);
        products.add(number, product);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void deleteProduct(int number) {
        products.remove(--number);
    }

    public void showList() {
        for (Product e : products)
            System.out.println(e);
    }

    @PostConstruct
    public void addProductAutomatic() {
        products.add(new Product("phone", 350, 320));
        products.add(new Product("lamp", 150, 520));
        products.add(new Product("sneakers", 200, 250));
    }
}
