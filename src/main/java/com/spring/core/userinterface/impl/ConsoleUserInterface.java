package com.spring.core.userinterface.impl;

import com.spring.core.model.Product;
import com.spring.core.service.ProductService;
import com.spring.core.userinterface.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * This class implements UserInterface interface which provides methods to manage products basket from console
 *
 * @author Katuranau Maksimilyan
 * @see UserInterface <-- There
 */
@Component
public class ConsoleUserInterface implements UserInterface {

    private Scanner scanner;
    private PrintStream outputStream;
    private boolean isClose;

    private ProductService productService;

    /**
     * @param productService is class which provides methods to manage basket
     * @param outputStream   is class to print data on the console
     * @param scanner        is class to get data from the console
     */
    @Autowired
    public ConsoleUserInterface(ProductService productService, PrintStream outputStream, Scanner scanner) {
        this.productService = productService;
        this.outputStream = outputStream;
        this.scanner = scanner;
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
        productService.getProductsFromBasket().forEach(outputStream::println);
    }

    @Override
    public void printAllProducts() {
        productService.getAllProducts().forEach(outputStream::println);
    }

    @Override
    public void showMenu() {
        outputStream.println("\nMenu:");
        for (UserMenuChoice choice : UserMenuChoice.values()) {
            if (choice != UserMenuChoice.UNDEFINED) {
                System.out.println(choice.getInformation());
            }
        }
    }

    @Override
    public UserMenuChoice getChoice() {
        UserMenuChoice choice = UserMenuChoice.get(scanner.next());
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
            outputStream.println(productService.getProduct(scanner.nextInt()));
        } catch(Exception e) {
            outputStream.println("You entered wrong index...");
        }
    }

    @Override
    public void deleteProduct() {
        outputStream.println("Enter product's index to be deleted from basket");
        try {
            productService.deleteProduct(scanner.nextInt());
        } catch(Exception e) {
            outputStream.println("You entered wrong index...");
        }
    }

    @Override
    public void addProduct() {
        outputStream.print("Enter product's index to be added to basket: ");
        try {
            productService.addProduct(scanner.nextInt());
        } catch(Exception e) {
            outputStream.println("You entered wrong index...");
        }
        /*
        Product product = new Product();
        outputStream.print("Adding product...\nEnter product's name: ");
        product.setName(scanner.next());
        try {
            outputStream.print("Enter product's price: ");
            product.setPrice(scanner.nextInt());
            outputStream.print("Enter product's weight: ");
            product.setWeight(scanner.nextInt());
            productService.addProduct(product);
        } catch (Exception e){
            scanner.next();
            outputStream.println("You entered wrong data...");
        }
        */
    }

    @Override
    public void updateProduct() {
        try {
            outputStream.print("Enter product's index to be updated: ");
            int index = scanner.nextInt();
            outputStream.print("Enter new product's index which will update old product: ");
            productService.updateProduct(index, scanner.nextInt());
        } catch(Exception e) {
            outputStream.println("You entered wrong index...");
        }
        /*
        Product product;
        outputStream.print("Enter product's index to be updated: ");
        try {
            product = productService.getProduct(scanner.nextInt());
            outputStream.print("Update product's name(" + product.getName() + "): ");
            product.setName(scanner.next());
            outputStream.print("Update product's price(" + product.getPrice() + "): ");
            product.setPrice(scanner.nextInt());
            outputStream.print("Update product's weight(" + product.getWeight() + "): ");
            product.setWeight(scanner.nextInt());
        } catch(IndexOutOfBoundsException e) {
            outputStream.println("You entered wrong index...");
        } catch (Exception ex){
            scanner.next();
            outputStream.println("You entered wrong data...");
        }
         */
    }
}
