package com.spring.core.userinterfase.impl;

import com.spring.core.userinterfase.UserInterface;
import org.springframework.stereotype.Component;

import java.io.PrintStream;

@Component
public class ConsoleUserInterface implements UserInterface {

    private PrintStream outputStream;

    void print() {
        outputStream.println();
    }
}
