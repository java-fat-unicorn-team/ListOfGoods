package com.spring.core;

import com.spring.core.config.TestConfig;
import com.spring.core.console_input.ConsoleInputValidator;
import com.spring.core.service.BasketService;
import com.spring.core.userinterface.UserInterface;
import com.spring.core.userinterface.impl.ConsoleUserInterface;
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

public class UserInterfaceTest {
    private static ApplicationContext context;
    private static UserInterface userInterface;
    private static BasketService basketService;
    private static PrintStream outputStream;
    private static ConsoleInputValidator inputStream;

    @BeforeClass
    public static void initialContext() {
        context = new AnnotationConfigApplicationContext(TestConfig.class);
        basketService = context.getBean(BasketService.class);
        outputStream = Mockito.mock(PrintStream.class);
        inputStream = Mockito.mock(ConsoleInputValidator.class);
        userInterface = new ConsoleUserInterface(basketService, outputStream, inputStream);
    }

    @After
    public void resetMocks() {
        Mockito.reset(outputStream);
        Mockito.reset(inputStream);
    }

    @Test
    public void testPrintProductsFromBasket() throws Exception {
        when(inputStream.nextInt()).thenReturn(5, 1, 3);
        userInterface.addProduct();
        userInterface.addProduct();
        userInterface.addProduct();
        int size = basketService.getProductsFromBasket().size();
        userInterface.printProductsFromBasket();
        verify(outputStream, times(size+3)).println(anyString());
    }

    @Test
    public void testPrintAllProducts() {
        userInterface.printAllProducts();
        verify(outputStream, atLeastOnce()).println(anyString());
    }

    @Test
    public void testAddProduct() {
        try {
            when(inputStream.nextInt()).thenReturn(0, 4, 2);
            userInterface.addProduct();
            userInterface.addProduct();
            userInterface.addProduct();
            verify(inputStream, times(3)).nextInt();
        } catch (Exception e) {
            fail("test add product is failed");
        }
    }

    @Test
    public void testDeleteProduct() {
        try {
            when(inputStream.nextInt()).thenReturn(6, 0);
            userInterface.addProduct();
            userInterface.deleteProduct();
            verify(inputStream, times(2)).nextInt();
        } catch (Exception e) {
            fail("test delete product is failed");
        }
    }

    @Test(expected = Exception.class)
    public void testDeleteNonexistentProduct() throws Exception {
        int size = basketService.getProductsFromBasket().size();
        when(inputStream.nextInt()).thenReturn(6, size + 1);
        userInterface.addProduct();
        userInterface.deleteProduct();
    }

    @Test
    public void testPrintProduct() {
        try {
            when(inputStream.nextInt()).thenReturn(3, 0);
            userInterface.addProduct();
            userInterface.printProduct();
            verify(inputStream, times(2)).nextInt();
        } catch (Exception e) {
            fail("test print product is failed");
        }
    }

    @Test(expected = Exception.class)
    public void testPrintNonexistentProduct() throws Exception {
        int size = basketService.getProductsFromBasket().size();
        when(inputStream.nextInt()).thenReturn(3, size + 1);
        userInterface.addProduct();
        userInterface.printProduct();
    }

    @Test
    public void testUpdateProduct() {
        try {
            when(inputStream.nextInt()).thenReturn(5, 0, 7);
            userInterface.addProduct();
            userInterface.updateProduct();
            verify(inputStream, times(3)).nextInt();
        } catch (Exception e) {
            fail("test update product is failed");
        }
    }

    @Test(expected = Exception.class)
    public void testUpdateNonexistentProduct() throws Exception {
        int size = basketService.getProductsFromBasket().size();
        when(inputStream.nextInt()).thenReturn(5, size + 1, 7);
        userInterface.addProduct();
        userInterface.updateProduct();
    }

    @Test
    public void testGetChoice() {
        when(inputStream.next()).thenReturn("1", "5");
        assertEquals(UserMenuChoice.PRINT_ALL, userInterface.getChoice());
        assertEquals(UserMenuChoice.ADD, userInterface.getChoice());
    }

    @Test
    public void testShowMenu() {
        userInterface.showMenu();
        verify(outputStream, times(8)).println(anyString());
    }
}
