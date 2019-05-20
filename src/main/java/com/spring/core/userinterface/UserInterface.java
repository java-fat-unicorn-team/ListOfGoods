package com.spring.core.userinterface;

import com.spring.core.userinterface.impl.UserMenuChoice;

/**
 * Use this interface if you want to create a new way to interaction with users
 * @author Katuranau Maksimilyan
 */
public interface UserInterface {
    /**
     * It is start point of program
     */
    void start();

    /**
     * This method shows menu
     */
    void showMenu();

    /**
     * You can choose there what to do
     *
     * @return user's choice
     */
    UserMenuChoice getChoice();

    /**
     * This method runs your choice
     *
     * @param userChoice
     */
    void runChoice(UserMenuChoice userChoice);

    /**
     * This method prints all products
     */
    void printAllProducts();

    /**
     * This method prints all products from basket
     */
    void printProductsFromBasket();

    /**
     * This method prints only one product you chose
     */
    void printProduct();

    /**
     * This method provides you ability to add product
     */
    void addProduct();

    /**
     * This method provides you ability to update product
     */
    void updateProduct();

    /**
     * This method deletes product you chose
     */
    void deleteProduct();
}
