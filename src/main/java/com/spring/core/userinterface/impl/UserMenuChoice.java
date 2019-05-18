package com.spring.core.userinterface.impl;

/**
 * This enum contains menu item which user can choose
 *
 * @author Katuranau Maksimilyan
 */
public enum UserMenuChoice {
    PRINT_ALL("1", "1)Print basket"),
    PRINT("2", "2)Print product"),
    DELETE("3", "3)Delete product"),
    ADD("4", "4)Add product"),
    UPDATE("5", "5)Update product"),
    CLOSE_APP("6", "6)Close program"),
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
