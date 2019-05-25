package com.spring.core.userinterface.impl;

import com.spring.core.console_input.ConsoleInputValidator;
import com.spring.core.model.Product;
import com.spring.core.service.BasketService;
import com.spring.core.userinterface.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.util.InputMismatchException;

/**
 * Class using for interaction with users through the console.
 *
 * @author Katuranau Maksimilyan
 * {@see UserInterface}
 */
@Component
public class ConsoleUserInterface implements UserInterface {

    /**
     * this class gets data from console.
     */
    private ConsoleInputValidator inputStream;
    /**
     * this class prints data on the console.
     */
    private PrintStream outputStream;
    /**
     * check if user entered close app.
     */
    private boolean isClose;
    /**
     * class provides methods to manage basket.
     */
    private BasketService basketService;

    /**
     * @param pBasketService is class which provides methods to manage basket
     * @param pOutputStream  is class to print data on the console
     * @param pInputStream   is class to get data from the console
     */
    @Autowired
    public ConsoleUserInterface(final BasketService pBasketService,
                                final PrintStream pOutputStream,
                                final ConsoleInputValidator pInputStream) {
        this.basketService = pBasketService;
        this.outputStream = pOutputStream;
        this.inputStream = pInputStream;
        this.isClose = false;
    }

    /**
     * main method in class to work with user.
     */
    @Override
    public final void start() {
        UserMenuChoice userChoice;
        while (!isClose) {
            showMenu();
            userChoice = getChoice();
            outputStream.println("***");
            try {
                runChoice(userChoice);
            } catch (Exception e) {
                outputStream.println("You entered wrong index...");
            }
        }
    }

    /**
     * print products from basket.
     */
    @Override
    public final void printProductsFromBasket() {
        for (Product product : basketService.getProductsFromBasket()) {
            outputStream.println(product.toString());
        }
    }

    /**
     * print all products.
     */
    @Override
    public final void printAllProducts() {
        for (Product product : basketService.getAllProducts()) {
            outputStream.println(product.toString());
        }
    }

    /**
     * prints menu.
     */
    @Override
    public final void showMenu() {
        outputStream.println("\nMenu:");
        for (UserMenuChoice choice : UserMenuChoice.values()) {
            if (choice != UserMenuChoice.UNDEFINED) {
                outputStream.println(choice.getInformation());
            }
        }
    }

    /**
     * get user's choice.
     * @return user's choice
     */
    @Override
    public final UserMenuChoice getChoice() {
        return UserMenuChoice.get(inputStream.next());
    }

    /**
     * run user's choice.
     * @param userChoice user's choice
     * @throws Exception if user's choice is failed
     */
    @Override
    public final void runChoice(final UserMenuChoice userChoice)
            throws Exception {
        switch (userChoice) {
            case PRINT_ALL:
                printAllProducts();
                break;
            case PRINT_BASKET:
                printProductsFromBasket();
                break;
            case PRINT:
                printProduct();
                break;
            case DELETE:
                deleteProduct();
                break;
            case ADD:
                addProduct();
                break;
            case UPDATE:
                updateProduct();
                break;
            case CLOSE_APP:
                isClose = true;
                break;
            default:
                outputStream.println("There is not such menu item...");
        }
    }

    /**
     * print product.
     * @throws Exception if user entered wrong index
     */
    @Override
    public final void printProduct() throws Exception {
        outputStream.println("Enter product's index to be printed");
        try {
            outputStream.println(basketService.getProduct(
                    inputStream.nextInt()).toString());
        } catch (InputMismatchException e) {
            inputStream.next();
            outputStream.println("You entered not int...");
        }
    }

    /**
     * delete product.
     * @throws Exception if there is no such product
     */
    @Override
    public final void deleteProduct() throws Exception {
        outputStream.println("Enter product's index to be deleted from basket");
        try {
            basketService.deleteProduct(inputStream.nextInt());
        } catch (InputMismatchException e) {
            inputStream.next();
            outputStream.println("You entered not int...");
        }
    }

    /**
     * add product.
     * @throws Exception if there is no such product
     */
    @Override
    public final void addProduct() throws Exception {
        outputStream.println("Enter product's index to be added to basket: ");
        try {
            basketService.addProduct(inputStream.nextInt());
        } catch (InputMismatchException e) {
            inputStream.next();
            outputStream.println("You entered not int...");
        }
    }

    /**
     * update product.
     * @throws Exception if there is no such product
     */
    @Override
    public final void updateProduct() throws Exception {
        outputStream.println("Enter product's index to be updated: ");
        try {
            int index = inputStream.nextInt();
            outputStream.println("Enter new product's "
                    + "index which will update old product: ");
            basketService.updateProduct(index, inputStream.nextInt());
        } catch (InputMismatchException e) {
            inputStream.next();
            outputStream.println("You entered not int...");
        }
    }
}
