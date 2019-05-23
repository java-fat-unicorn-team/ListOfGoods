package com.spring.core.userinterface;

import com.spring.core.console_input.ConsoleInputValidator;
import com.spring.core.service.BasketService;
import com.spring.core.userinterface.impl.UserMenuChoice;

import java.io.PrintStream;

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
    void runChoice(UserMenuChoice userChoice)  throws Exception;

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
    void printProduct() throws Exception;

    /**
     * This method provides you ability to add product
     */
    void addProduct() throws Exception;

    /**
     * This method provides you ability to update product
     */
    void updateProduct() throws Exception;

    /**
     * This method deletes product you chose
     */
    void deleteProduct() throws Exception;

    /**
     * This method is used for test
     * @return BasketService is object which provides methods to manage products basket
     */
    BasketService getBasketService();

    /**
     * This method is used for test
     * @return ConsoleInputValidator is object which provides methods to get input data
     */
    ConsoleInputValidator getConsoleInputValidator();

    /**
     * This method is used for test
     * @return PrintStream is object which provides methods to print data
     */
    PrintStream getPrintStream();
}
