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
import org.springframework.util.ReflectionUtils;

import java.io.PrintStream;
import java.lang.reflect.Field;
import java.util.InputMismatchException;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

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
        Field basketServiceField = ReflectionUtils.findField(ConsoleUserInterface.class, "basketService");
        Field outputStreamField = ReflectionUtils.findField(ConsoleUserInterface.class, "outputStream");
        Field inputStreamField = ReflectionUtils.findField(ConsoleUserInterface.class, "inputStream");
        ReflectionUtils.makeAccessible(basketServiceField);
        ReflectionUtils.makeAccessible(outputStreamField);
        ReflectionUtils.makeAccessible(inputStreamField);
        basketService = (BasketService) ReflectionUtils.getField(basketServiceField, userInterface);
        outputStream = (PrintStream) ReflectionUtils.getField(outputStreamField, userInterface);
        inputStream = (ConsoleInputValidator) ReflectionUtils.getField(inputStreamField, userInterface);
    }

    @After
    public void resetMocks() {
        Mockito.reset(outputStream);
        Mockito.reset(inputStream);
    }

    @Test
    public void testPrintProductsFromBasket() throws Exception {
        when(inputStream.nextInt()).thenReturn(3, 7);
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
        when(inputStream.nextInt()).thenReturn(size);
        userInterface.printProduct();
    }

    @Test
    public void testAddProduct() throws Exception {
        int sizeBefore = basketService.getProductsFromBasket().size();
        when(inputStream.nextInt()).thenReturn(3, 7);
        userInterface.addProduct();
        userInterface.addProduct();
        int sizeAfter = basketService.getProductsFromBasket().size();
        assertEquals(sizeBefore + 2, sizeAfter);
    }

    @Test
    public void testAddProductFail() throws Exception {
        int sizeBefore = basketService.getProductsFromBasket().size();
        when(inputStream.nextInt()).thenThrow(InputMismatchException.class);
        userInterface.addProduct();
        int sizeAfter = basketService.getProductsFromBasket().size();
        assertTrue(sizeBefore == sizeAfter);
    }

    @Test(expected = Exception.class)
    public void testDeleteNonexistentProduct() throws Exception {
        int size = basketService.getProductsFromBasket().size();
        when(inputStream.nextInt()).thenReturn(size);
        userInterface.deleteProduct();
    }

    @Test
    public void testDeleteProduct() throws Exception {
        when(inputStream.nextInt()).thenReturn(5, 0);
        userInterface.addProduct();
        int sizeBefore = basketService.getProductsFromBasket().size();
        userInterface.deleteProduct();
        int sizeAfter = basketService.getProductsFromBasket().size();
        assertEquals(sizeBefore - 1, sizeAfter);
    }

    @Test
    public void testDeleteProductFail() throws Exception {
        when(inputStream.nextInt()).thenReturn(5);
        userInterface.addProduct();
        int sizeBefore = basketService.getProductsFromBasket().size();
        when(inputStream.nextInt()).thenThrow(InputMismatchException.class);
        userInterface.deleteProduct();
        int sizeAfter = basketService.getProductsFromBasket().size();
        assertTrue(sizeBefore == sizeAfter);
    }

    @Test
    public void testUpdateProduct() throws Exception {
        when(inputStream.nextInt()).thenReturn(5, 3, 1, 7);
        userInterface.addProduct();
        userInterface.addProduct();
        userInterface.updateProduct();
        assertEquals(basketService.getProduct(1).getWeight(),
                basketService.getAllProducts().get(7).getWeight());
        assertEquals(basketService.getProduct(1).getName(),
                basketService.getAllProducts().get(7).getName());
    }

    @Test
    public void testUpdateProductFail() throws Exception {
        when(inputStream.nextInt()).thenReturn(5, 3);
        userInterface.addProduct();
        userInterface.addProduct();
        when(inputStream.nextInt()).thenThrow(InputMismatchException.class);
        userInterface.updateProduct();
        assertNotEquals(basketService.getProduct(1).getWeight(),
                basketService.getAllProducts().get(7).getWeight());
        assertNotEquals(basketService.getProduct(1).getName(),
                basketService.getAllProducts().get(7).getName());
    }

    @Test
    public void testGetChoice() {
        when(inputStream.next()).thenReturn("3", "5", "9");
        assertEquals(UserMenuChoice.PRINT, userInterface.getChoice());
        assertEquals(UserMenuChoice.ADD, userInterface.getChoice());
        assertEquals(UserMenuChoice.UNDEFINED, userInterface.getChoice());
    }
}
