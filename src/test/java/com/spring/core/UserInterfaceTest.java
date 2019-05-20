package com.spring.core;

import com.spring.core.config.AppConfig;
import com.spring.core.console_input.ConsoleInputValidator;
import com.spring.core.service.BasketService;
import com.spring.core.userinterface.impl.ConsoleUserInterface;
import com.spring.core.userinterface.impl.UserMenuChoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserInterfaceTest {

    private ApplicationContext context;
    private ConsoleUserInterface userInterface;
    private BasketService basketService;
    private PrintStream outputStream;
    private ConsoleInputValidator inputStream;

    @Before
    public void initialContext() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        basketService = context.getBean(BasketService.class);
        outputStream = Mockito.mock(PrintStream.class);
        inputStream = Mockito.mock(ConsoleInputValidator.class);
        userInterface = new ConsoleUserInterface(basketService, outputStream, inputStream);
    }

    @Test
    public void testPrintProductsFromBasket() {
        when(inputStream.nextInt()).thenReturn(5, 1, 3);
        userInterface.addProduct();
        userInterface.addProduct();
        userInterface.addProduct();
        userInterface.printProductsFromBasket();
        verify(outputStream, times(3)).println(anyString());
    }

    @Test
    public void testPrintAllProducts() {
        userInterface.printAllProducts();
        verify(outputStream, atLeastOnce()).println(anyString());
    }

    @Test
    public void testAddProduct() {
        when(inputStream.nextInt()).thenReturn(5, 1, 3);
        userInterface.addProduct();
        userInterface.addProduct();
        userInterface.addProduct();
        verify(inputStream, times(3)).nextInt();
    }

    @Test
    public void testDeleteProduct() {
        when(inputStream.nextInt()).thenReturn(1);
        userInterface.deleteProduct();
        verify(inputStream, times(1)).nextInt();
    }

    @Test
    public void testPrintProduct() {
        when(inputStream.nextInt()).thenReturn(3);
        userInterface.printProduct();
        verify(inputStream, times(1)).nextInt();
    }

    @Test
    public void testUpdateProduct() {
        when(inputStream.nextInt()).thenReturn(3, 7);
        userInterface.updateProduct();
        verify(inputStream, times(2)).nextInt();
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
