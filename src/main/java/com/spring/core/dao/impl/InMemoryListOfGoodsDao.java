package com.spring.core.dao.impl;

import com.spring.core.dao.ListOfGoodsDao;
import com.spring.core.model.ListOfGoods;
import com.spring.core.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * This class gets list of products and provides methods to get them from it.
 *
 * @author Katuranau Maksimilyan
 */
@Component
public class InMemoryListOfGoodsDao implements ListOfGoodsDao {
    /**
     * list of products.
     */
    private ListOfGoods listOfGoods;

    /**
     * @param pListOfGoods is class which contain list of products
     */
    @Autowired
    public InMemoryListOfGoodsDao(final ListOfGoods pListOfGoods) {
        this.listOfGoods = pListOfGoods;
    }

    /**
     * @return List is list of all products
     */
    @Override
    public final List<Product> getProducts() {
        return listOfGoods.getListOfGoods();
    }

    /**
     * @param index is product's index you want to get
     * @return Product is a product with an index that was provided
     */
    @Override
    public final Product getProduct(final int index) {
        return listOfGoods.getListOfGoods().get(index);
    }
}
