package com.spring.core.console_input;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is used to enter information from the stream.
 *
 * @author Katuranau Maksimilyan
 */

public class ConsoleInputValidator {

    /**
     * class to get data from console.
     */
    private Scanner scanner;

    /**
     * @param pScanner is class to get data from console
     */
    @Autowired
    public ConsoleInputValidator(final Scanner pScanner) {
        scanner = pScanner;
    }

    /**
     * get next line from console.
     *
     * @return line entered in console
     */
    public String next() {
        return scanner.next();
    }

    /**
     * get next int from console.
     *
     * @throws InputMismatchException if entered not int
     * @return int variable entered in console
     */
    public int nextInt() throws InputMismatchException {
        return scanner.nextInt();
    }
}
