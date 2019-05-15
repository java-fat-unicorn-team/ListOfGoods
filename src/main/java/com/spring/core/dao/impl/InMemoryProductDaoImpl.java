package com.spring.core.dao.impl;

import com.spring.core.dao.ProductDao;
import com.spring.core.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryProductDaoImpl implements ProductDao {

    private final List<Product> listOfGoods;

    @Autowired
    public InMemoryProductDaoImpl(List<Product> listOfGoods) {
        this.listOfGoods = listOfGoods;
    }

    @Override
    public List<Product> getListOfProducts() {
        return listOfGoods;
    }

    @Override
    public Product getProduct(int number) {
        return listOfGoods.get(number);
    }

    @Override
    public void updateProduct(Product product, int number) {
        listOfGoods.set(number, product);
    }

    @Override
    public void addProduct(Product product) {
        listOfGoods.add(product);
    }

    @Override
    public void deleteProduct(int number) {
        listOfGoods.remove(number);
    }
}
