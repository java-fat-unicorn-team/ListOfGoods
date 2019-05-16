package com.spring.core.dao.impl;

import com.spring.core.dao.ProductDao;
import com.spring.core.model.ListOfGoods;
import com.spring.core.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryProductDaoImpl implements ProductDao {

    private final ListOfGoods listOfGoods;

    @Autowired
    public InMemoryProductDaoImpl(ListOfGoods listOfGoods) {
        this.listOfGoods = listOfGoods;
    }

    @Override
    public ListOfGoods getListOfProducts() {
        return listOfGoods;
    }

    @Override
    public Product getProduct(int number) {
        return listOfGoods.getProducts().get(number);
    }

    @Override
    public void updateProduct(Product product, int number) {
        listOfGoods.getProducts().set(number, product);
    }

    @Override
    public void addProduct(Product product) {
        listOfGoods.getProducts().add(product);
    }

    @Override
    public void deleteProduct(int number) {
        listOfGoods.getProducts().remove(number);
    }
}
