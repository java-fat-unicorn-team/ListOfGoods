package com.spring.core.userinterface.impl;

import com.spring.core.console_input.ConsoleInputValidator;
import com.spring.core.model.Product;
import com.spring.core.service.BasketService;
import com.spring.core.userinterface.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.PrintStream;

/**
 * Class using for interaction with users through the console
 *
 * @author Katuranau Maksimilyan
 * {@see UserInterface}
 */
@Component
public class ConsoleUserInterface implements UserInterface {

    private ConsoleInputValidator inputStream;
    private PrintStream outputStream;
    private boolean isClose;

    private BasketService basketService;

    /**
     * @param basketService is class which provides methods to manage basket
     * @param outputStream  is class to print data on the console
     * @param inputStream   is class to get data from the console
     */
    @Autowired
    public ConsoleUserInterface(BasketService basketService, PrintStream outputStream, ConsoleInputValidator inputStream) {
        this.basketService = basketService;
        this.outputStream = outputStream;
        this.inputStream = inputStream;
        this.isClose = false;
    }

    @Override
    public void start() {
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

    @Override
    public void printProductsFromBasket() {
        for (Product product : basketService.getProductsFromBasket()) {
            outputStream.println(product.toString());
        }
    }

    @Override
    public void printAllProducts() {
        for (Product product : basketService.getAllProducts()) {
            outputStream.println(product.toString());
        }
    }

    @Override
    public void showMenu() {
        outputStream.println("\nMenu:");
        for (UserMenuChoice choice : UserMenuChoice.values()) {
            if (choice != UserMenuChoice.UNDEFINED) {
                outputStream.println(choice.getInformation());
            }
        }
    }

    @Override
    public UserMenuChoice getChoice() {
        return UserMenuChoice.get(inputStream.next());
    }

    @Override
    public void runChoice(UserMenuChoice userChoice) throws Exception {
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

    @Override
    public void printProduct() throws Exception {
        outputStream.println("Enter product's index to be printed");
        outputStream.println(basketService.getProduct(inputStream.nextInt()).toString());
    }

    @Override
    public void deleteProduct() throws Exception {
        outputStream.println("Enter product's index to be deleted from basket");
        basketService.deleteProduct(inputStream.nextInt());
    }

    @Override
    public void addProduct() throws Exception {
        outputStream.println("Enter product's index to be added to basket: ");
        basketService.addProduct(inputStream.nextInt());
    }

    @Override
    public void updateProduct() throws Exception {
        outputStream.println("Enter product's index to be updated: ");
        int index = inputStream.nextInt();
        outputStream.println("Enter new product's index which will update old product: ");
        basketService.updateProduct(index, inputStream.nextInt());
    }

    public BasketService getBasketService() {
        return basketService;
    }

    @Override
    public ConsoleInputValidator getConsoleInputValidator() {
        return inputStream;
    }

    @Override
    public PrintStream getPrintStream() {
        return outputStream;
    }
}
