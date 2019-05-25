package com.spring.core.userinterface;

import com.spring.core.userinterface.impl.UserMenuChoice;

/**
 * This interface is used to create a way to interaction with users.
 *
 * @author Katuranau Maksimilyan
 */
public interface UserInterface {
    /**
     * It is start point of program.
     */
    void start();

    /**
     * This method shows menu.
     */
    void showMenu();

    /**
     * You can choose there what to do.
     *
     * @return user's choice
     */
    UserMenuChoice getChoice();

    /**
     * This method runs your choice.
     *
     * @param userChoice user's choice
     * @throws Exception if user's choice is failed
     */
    void runChoice(UserMenuChoice userChoice) throws Exception;

    /**
     * This method prints all products.
     */
    void printAllProducts();

    /**
     * This method prints all products from basket.
     */
    void printProductsFromBasket();

    /**
     * This method prints only one product you chose.
     * @throws Exception if user enter wrong index
     */
    void printProduct() throws Exception;

    /**
     * This method provides you ability to add product.
     * @throws Exception if user enter wrong index
     */
    void addProduct() throws Exception;

    /**
     * This method provides you ability to update product.
     * @throws Exception if user enter wrong index
     */
    void updateProduct() throws Exception;

    /**
     * This method deletes product you chose.
     * @throws Exception if user enter wrong index
     */
    void deleteProduct() throws Exception;
}
