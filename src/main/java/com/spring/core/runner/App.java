package com.spring.core.runner;

import com.spring.core.config.AppConfig;
import com.spring.core.userinterface.UserInterface;
import org.springframework.context.ApplicationContext;
import org.springframework.context.
        annotation.AnnotationConfigApplicationContext;

/**
 * This project is spring console application.
 * which provides ability to manage the list of goods
 *
 * @author Katuranau Maksimilyan
 * @version 1.0
 * @since 2019-05-17
 */
final class App {
    /**
     * private constructor.
     */
    private App() {
    }
    /**
     * This method is start point of application.
     *
     * @param args arguments
     */
    public static void main(final String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserInterface userInterface = context.getBean(UserInterface.class);
        userInterface.start();
    }
}
