package com.spring.core.dao.impl;

import com.spring.core.dao.ListOfGoodsDao;
import com.spring.core.model.ListOfGoods;
import com.spring.core.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InMemoryListOfGoodsDaoImpl implements ListOfGoodsDao {
    ListOfGoods listOfGoods;

    @Autowired
    public InMemoryListOfGoodsDaoImpl(ListOfGoods listOfGoods) {
        this.listOfGoods = listOfGoods;
    }

    @Override
    public List<Product> getProducts() {
        return listOfGoods.getListOfGoods();
    }

    @Override
    public Product getProduct(int index) {
        return listOfGoods.getListOfGoods().get(index);
    }
}
