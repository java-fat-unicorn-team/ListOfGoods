package com.spring.core;

import com.spring.core.config.AppConfig;
import com.spring.core.userinterface.UserInterface;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * This project is spring console application
 * which provides ability to manage the list of goods
 *
 * @author Katuranau Maksimilyan
 * @version 1.0
 * @since 2019-05-17
 */

public class App {
    /**
     * This method is start point of application
     *
     * @param args is String[] value arguments passed when the application starts
     */
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserInterface userInterface = context.getBean(UserInterface.class);
        userInterface.start();
    }
}
