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
 * @author Katuranau Maksimilyan
 * {@see UserInterface}
 */
@Component
public class ConsoleUserInterface implements UserInterface {

    private ConsoleInputValidator inputStream;
    private PrintStream outputStream;
    private boolean isClose;

    private BasketService productService;

    /**
     * @param basketService is class which provides methods to manage basket
     * @param outputStream  is class to print data on the console
     * @param inputStream   is class to get data from the console
     */
    @Autowired
    public ConsoleUserInterface(BasketService basketService, PrintStream outputStream, ConsoleInputValidator inputStream) {
        this.productService = basketService;
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
            runChoice(userChoice);
        }
    }

    @Override
    public void printProductsFromBasket() {
        for (Product product : productService.getProductsFromBasket()) {
            outputStream.println(product.toString());
        }
    }

    @Override
    public void printAllProducts() {
        for (Product product : productService.getAllProducts()) {
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
        UserMenuChoice choice = UserMenuChoice.get(inputStream.next());
        return choice;
    }

    @Override
    public void runChoice(UserMenuChoice userChoice) {
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
    public void printProduct() {
        outputStream.println("Enter product's index to be printed");
        try {
            outputStream.println(productService.getProduct(inputStream.nextInt()));
        } catch (Exception e) {
            outputStream.println("You entered wrong index...");
        }
    }

    @Override
    public void deleteProduct() {
        outputStream.println("Enter product's index to be deleted from basket");
        try {
            productService.deleteProduct(inputStream.nextInt());
        } catch (Exception e) {
            outputStream.println("You entered wrong index...");
        }
    }

    @Override
    public void addProduct() {
        outputStream.print("Enter product's index to be added to basket: ");
        try {
            productService.addProduct(inputStream.nextInt());
        } catch (Exception e) {
            outputStream.println("You entered wrong index...");
        }
    }

    @Override
    public void updateProduct() {
        try {
            outputStream.print("Enter product's index to be updated: ");
            int index = inputStream.nextInt();
            outputStream.print("Enter new product's index which will update old product: ");
            productService.updateProduct(index, inputStream.nextInt());
        } catch (Exception e) {
            outputStream.println("You entered wrong index...");
        }
    }
}
