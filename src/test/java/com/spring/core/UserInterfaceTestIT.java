package com.spring.core;

import com.spring.core.config.TestConfig;
import com.spring.core.console_input.ConsoleInputValidator;
import com.spring.core.service.BasketService;
import com.spring.core.userinterface.UserInterface;
import com.spring.core.userinterface.impl.UserMenuChoice;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

public class UserInterfaceTestIT {
    private static ApplicationContext context;
    private static UserInterface userInterface;
    private static BasketService basketService;
    private static PrintStream outputStream;
    private static ConsoleInputValidator inputStream;

    @BeforeClass
    public static void initialContext() {
        context = new AnnotationConfigApplicationContext(TestConfig.class);
        userInterface = context.getBean(UserInterface.class);
        basketService = userInterface.getBasketService();
        outputStream = userInterface.getPrintStream();
        inputStream = userInterface.getConsoleInputValidator();
    }

    @After
    public void resetMocks() {
        Mockito.reset(outputStream);
        Mockito.reset(inputStream);
    }

    @Test
    public void testPrintProductsFromBasket() throws Exception {
        Mockito.when(inputStream.nextInt()).thenReturn(3, 7);
        userInterface.addProduct();
        userInterface.addProduct();
        int size = basketService.getProductsFromBasket().size();
        userInterface.printProductsFromBasket();
        Mockito.verify(outputStream, Mockito.times(size + 2)).println(anyString());
    }

    @Test
    public void testPrintAllProducts() {
        outputStream.flush();
        int size = basketService.getAllProducts().size();
        userInterface.printAllProducts();
        Mockito.verify(outputStream, Mockito.times(size)).println(anyString());
    }

    @Test(expected = Exception.class)
    public void testPrintNonexistentProduct() throws Exception {
        int size = basketService.getProductsFromBasket().size();
        Mockito.when(inputStream.nextInt()).thenReturn(size);
        userInterface.printProduct();
    }

    @Test
    public void testAddProduct() throws Exception {
        int sizeBefore = basketService.getProductsFromBasket().size();
        Mockito.when(inputStream.nextInt()).thenReturn(3, 7);
        userInterface.addProduct();
        userInterface.addProduct();
        int sizeAfter = basketService.getProductsFromBasket().size();
        assertEquals(sizeBefore + 2, sizeAfter);
}

    @Test(expected = Exception.class)
    public void testDeleteNonexistentProduct() throws Exception {
        int size = basketService.getProductsFromBasket().size();
        Mockito.when(inputStream.nextInt()).thenReturn(size);
        userInterface.deleteProduct();
    }

    @Test
    public void testDeleteProduct() throws Exception{
        Mockito.when(inputStream.nextInt()).thenReturn(5, 0);
        userInterface.addProduct();
        int sizeBefore = basketService.getProductsFromBasket().size();
        userInterface.deleteProduct();
        int sizeAfter = basketService.getProductsFromBasket().size();
        assertEquals(sizeBefore - 1, sizeAfter);
    }

    @Test
    public void testUpdateProduct() throws Exception {
        Mockito.when(inputStream.nextInt()).thenReturn(5, 3, 1, 7);
        userInterface.addProduct();
        userInterface.addProduct();
        userInterface.updateProduct();
        assertEquals(basketService.getProduct(1), basketService.getAllProducts().get(7));
    }

    @Test
    public void testGetChoice() {
        Mockito.when(inputStream.next()).thenReturn("3");
        assertEquals(UserMenuChoice.PRINT, userInterface.getChoice());
    }
}
