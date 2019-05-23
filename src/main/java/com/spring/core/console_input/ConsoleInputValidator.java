package com.spring.core.console_input;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Scanner;

/**
 * This class is used to enter information from the stream
 *
 * @author Katuranau Maksimilyan
 */

public class ConsoleInputValidator {

    private Scanner scanner;

    /**
     * @param inputStream is the stream from which information will be scanned
     */
    @Autowired
    public ConsoleInputValidator(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    public String next() {
        return scanner.next();
    }

    public int nextInt() {
        return scanner.nextInt();
    }

    public boolean hasNext() {
        return scanner.hasNextLine();
    }

    public boolean hasNextInt() {
        return scanner.hasNextInt();
    }
    public void clear(){

    }
}
