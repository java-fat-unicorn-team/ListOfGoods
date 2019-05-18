package com.spring.core;

import com.spring.core.service.ProductService;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserInterfaceTest {
    @Mock
    private ProductService productService;
}
