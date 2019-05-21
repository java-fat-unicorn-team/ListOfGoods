package com.spring.core;

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
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserInterfaceTestIT {
    private static ApplicationContext context;
    private static UserInterface userInterface;
    private static BasketService basketService;
    private static PrintStream outputStream;
    private static ConsoleInputValidator inputStream;

    @BeforeClass
    public static void initialContest() {
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
    public void testPrintProductsFromBasket() {
        try {
            when(inputStream.nextInt()).thenReturn(3, 7);
            userInterface.addProduct();
            userInterface.addProduct();
            int size = basketService.getProductsFromBasket().size();
            userInterface.printProductsFromBasket();
            verify(outputStream, times(size)).println(anyString());
        } catch (Exception e) {
            fail("test print products from basket failed");
        }
    }

    @Test
    public void testPrintAllProducts() {
        outputStream.flush();
        int size = basketService.getAllProducts().size();
        userInterface.printAllProducts();
        verify(outputStream, times(size)).println(anyString());
    }

    @Test(expected = Exception.class)
    public void testPrintProduct() throws Exception {
        when(inputStream.nextInt()).thenReturn(15);
        userInterface.printProduct();
    }

    @Test
    public void testAddProduct() {
        try {
            int sizeBefore = basketService.getProductsFromBasket().size();
            when(inputStream.nextInt()).thenReturn(3, 7);
            userInterface.addProduct();
            userInterface.addProduct();
            int sizeAfter = basketService.getProductsFromBasket().size();
            assertEquals(sizeBefore + 2, sizeAfter);
        } catch (Exception e) {
            fail("test add products failed");
        }
    }

    @Test(expected = Exception.class)
    public void testDeleteNonexistentProduct() throws Exception {
        when(inputStream.nextInt()).thenReturn(15);
        userInterface.deleteProduct();
    }

    @Test
    public void testDeleteProduct() {
        try {
            when(inputStream.nextInt()).thenReturn(5, 0);
            userInterface.addProduct();
            int sizeBefore = basketService.getProductsFromBasket().size();
            userInterface.deleteProduct();
            int sizeAfter = basketService.getProductsFromBasket().size();
            assertEquals(sizeBefore - 1, sizeAfter);
        } catch (Exception e) {
            fail("test delete product failed");
        }
    }

    @Test
    public void testUpdateProduct() {
        try {
            when(inputStream.nextInt()).thenReturn(5, 3, 1, 7);
            userInterface.addProduct();
            userInterface.addProduct();
            userInterface.updateProduct();
            assertEquals(basketService.getProduct(1), basketService.getAllProducts().get(7));
        } catch (Exception e) {
            fail("test update product failed");
        }
    }

    @Test
    public void testGetChoice() {
        when(inputStream.next()).thenReturn("3");
        assertEquals(UserMenuChoice.PRINT, userInterface.getChoice());
    }
}
