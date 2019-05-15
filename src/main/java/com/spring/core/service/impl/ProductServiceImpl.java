package com.spring.core.service.impl;

import com.spring.core.dao.ProductDao;
import com.spring.core.model.Product;
import com.spring.core.service.ProductService;
import com.spring.core.userinterfase.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;
    private final UserInterface userInterface;

    @Autowired
    public ProductServiceImpl(ProductDao productDao, UserInterface userInterface) {
        this.productDao = productDao;
        this.userInterface = userInterface;
    }

    public List<Product> getListOfProducts() {

        return productDao.getListOfProducts();
    }

    public Product getProduct(int number) {
        return productDao.getProduct(--number);
    }

    public void updateProduct(Product product, int number) {
        productDao.updateProduct(product, number);

    }

    public void addProduct(Product product) {
        productDao.addProduct(product);
    }

    public void deleteProduct(int number) {
        productDao.deleteProduct(--number);
    }

}
