package com.spring.core;

import com.spring.core.console_input.ConsoleInputValidator;
import com.spring.core.model.Product;
import com.spring.core.service.BasketService;
import com.spring.core.userinterface.impl.ConsoleUserInterface;
import com.spring.core.userinterface.impl.UserMenuChoice;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserInterfaceTest {

    @Mock
    private BasketService basketService;
    @Mock
    private PrintStream outputStream;
    @Mock
    private ConsoleInputValidator inputStream;
    @InjectMocks
    private ConsoleUserInterface userInterface;

    private static List<Product> list;

    @BeforeClass
    public static void initialContext() {
        list = new ArrayList<>() {{
            add(new Product("phone", new BigDecimal(790.22), 340));
            add(new Product("pan", new BigDecimal(9.12), 15));
            add(new Product("computer", new BigDecimal(3200.00), 2100));
        }};
    }

    @After
    public void resetMocks() {
        Mockito.reset(basketService);
        Mockito.reset(outputStream);
        Mockito.reset(inputStream);
    }

    @Test
    public void testPrintProductsFromBasket() {
        when(basketService.getProductsFromBasket()).thenReturn(list);
        userInterface.printProductsFromBasket();
        verify(outputStream, times(3)).println(anyString());
        verify(basketService).getProductsFromBasket();
    }

    @Test
    public void testPrintAllProducts() {
        when(basketService.getAllProducts()).thenReturn(list);
        userInterface.printAllProducts();
        verify(outputStream, times(3)).println(anyString());
        verify(basketService).getAllProducts();
    }

    @Test
    public void testPrintProduct() throws Exception {
        when(inputStream.nextInt()).thenReturn(2);
        when(basketService.getProduct(2)).thenReturn(list.get(2));
        userInterface.printProduct();
        verify(inputStream, times(1)).nextInt();
        verify(outputStream, times(2)).println(anyString());
        verify(basketService).getProduct(2);

    }

    @Test(expected = Exception.class)
    public void testPrintNonexistentProduct() throws Exception {
        when(inputStream.nextInt()).thenReturn(20);
        when(basketService.getProduct(20)).thenThrow(Exception.class);
        userInterface.printProduct();
    }

    @Test
    public void testAddProduct() throws Exception {
        when(inputStream.nextInt()).thenReturn(2, 5, 7);
        userInterface.addProduct();
        userInterface.addProduct();
        userInterface.addProduct();
        verify(outputStream, times(3)).println(anyString());
        verify(basketService, times(3)).addProduct(anyInt());
    }

    @Test
    public void testDeleteProduct() throws Exception {
        when(inputStream.nextInt()).thenReturn(2, 5, 7);
        userInterface.deleteProduct();
        userInterface.deleteProduct();
        userInterface.deleteProduct();
        verify(outputStream, times(3)).println(anyString());
        verify(basketService, times(3)).deleteProduct(anyInt());
    }

    @Test(expected = Exception.class)
    public void testDeleteNonexistentProduct() throws Exception {
        when(inputStream.nextInt()).thenThrow(Exception.class);
        userInterface.deleteProduct();
    }

    @Test
    public void testUpdateProduct() throws Exception {
        when(inputStream.nextInt()).thenReturn(2, 5);
        userInterface.updateProduct();
        verify(outputStream, times(2)).println(anyString());
        verify(basketService).updateProduct(anyInt(), anyInt());
    }

    @Test(expected = Exception.class)
    public void testUpdateNonexistentProduct() throws Exception {
        when(inputStream.nextInt()).thenThrow(Exception.class);
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
