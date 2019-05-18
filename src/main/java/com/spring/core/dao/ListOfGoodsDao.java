package com.spring.core.dao;

import com.spring.core.model.Product;

import java.util.List;

public interface ListOfGoodsDao {
    List<Product> getProducts();
    Product getProduct(int index);
}
