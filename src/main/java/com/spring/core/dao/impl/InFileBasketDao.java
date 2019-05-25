package com.spring.core.dao.impl;

import com.spring.core.config.BasketInitializer;
import com.spring.core.dao.BasketDao;
import com.spring.core.dao.ListOfGoodsDao;
import com.spring.core.model.Basket;
import com.spring.core.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This class provides methods to manage products basket.
 * products are stored in file
 *
 * @author Katuranau Maksimilyan
 * {@see BasketDao}
 */
@Repository
@Profile("fromFile")
public class InFileBasketDao implements BasketDao {

    /**
     * class which contains list of basket's products and loads from file.
     */
    private Basket basket;
    /**
     * class which contains list of all products and loads from file.
     */
    private final ListOfGoodsDao listOfProducts;
    /**
     * class that receives and saves to the file a list of products.
     */
    private final BasketInitializer basketInitializer;

    /**
     * @param pListOfProducts list of products
     * @param pBasketInitializer basket initializer
     * @param pBasket basket initializer
     */
    @Autowired
    public InFileBasketDao(final ListOfGoodsDao pListOfProducts,
                           final BasketInitializer pBasketInitializer,
                           final Basket pBasket) {
        this.listOfProducts = pListOfProducts;
        this.basketInitializer = pBasketInitializer;
        this.basket = pBasket;
    }

    /**
     * list of all products.
     * @return list of products
     */
    @Override
    public final List<Product> getAllProducts() {
        return listOfProducts.getProducts();
    }

    /**
     * list of products from basket.
     * @return list of prodacts
     */
    @Override
    public final List<Product> getProductsFromBasket() {
        return basket.getBasket();
    }

    /**
     * product with entered index.
     * @param index is index of product to be obtained
     * @return product with entered index
     */
    @Override
    public final Product getProduct(final int index) {
        return basket.getBasket().get(index);
    }

    /**
     * update product.
     * @param index         is index of product from basket to be updated
     * @param indexInBasket is index of product to be added
     * @throws Exception if entered wrong index
     */
    @Override
    public final void updateProduct(final int index,
                              final int indexInBasket) throws Exception {
        basket.getBasket().set(index, listOfProducts.getProduct(indexInBasket));
        basketInitializer.updateBasket(basket);
    }

    /**
     * add product.
     * @param indexInBasket is index of product to be added
     * @throws Exception if entered wrong index
     */
    @Override
    public final void addProduct(final int indexInBasket) throws Exception {
        basket.getBasket().add(listOfProducts.getProduct(indexInBasket));
        basketInitializer.updateBasket(basket);

    }

    /**
     * delete product.
     * @param index product's index from basket to be deleted
     * @throws Exception if entered wrong index
     */
    @Override
    public final void deleteProduct(final int index) throws Exception {
        basket.getBasket().remove(index);
        basketInitializer.updateBasket(basket);
    }
}
