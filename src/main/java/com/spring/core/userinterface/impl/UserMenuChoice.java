package com.spring.core.userinterface.impl;

/**
 * This enum contains menu item which user can choose
 *
 * @author Katuranau Maksimilyan
 */
public enum UserMenuChoice {
    PRINT_ALL("1", "1)Print all products"),
    PRINT_BASKET("2", "2)Print products from basket"),
    PRINT("3", "3)Print product"),
    DELETE("4", "4)Delete product"),
    ADD("5", "5)Add product"),
    UPDATE("6", "6)Update product"),
    CLOSE_APP("7", "7)Close program"),
    UNDEFINED("", "");

    String choice;
    String information;

    /**
     * @param choice      is a number of the menu item
     * @param information is description of the menu item
     */
    UserMenuChoice(String choice, String information) {
        this.choice = choice;
        this.information = information;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getInformation() {
        return information;
    }

    /**
     * @param userChoice is number of the menu item user chose
     * @return is the menu item user chose
     */
    public static UserMenuChoice get(String userChoice) {
        for (UserMenuChoice choice : UserMenuChoice.values()) {
            if (choice.getChoice().equals(userChoice)) {
                return choice;
            }
        }
        return UNDEFINED;
    }
}
