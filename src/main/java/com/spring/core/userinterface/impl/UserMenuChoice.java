package com.spring.core.userinterface.impl;

/**
 * This enum contains menu item which user can choose.
 *
 * @author Katuranau Maksimilyan
 */
public enum UserMenuChoice {
    /**
     * Print all products.
     */
    PRINT_ALL("1", "1)Print all products"),
    /**
     * Print products from basket.
     */
    PRINT_BASKET("2", "2)Print products from basket"),
    /**
     * Print one product.
     */
    PRINT("3", "3)Print product"),
    /**
     * Delete product.
     */
    DELETE("4", "4)Delete product"),
    /**
     * Add product.
     */
    ADD("5", "5)Add product"),
    /**
     * Update product.
     */
    UPDATE("6", "6)Update product"),
    /**
     * Close program.
     */
    CLOSE_APP("7", "7)Close program"),
    /**
     * undefined, if user's choice is wrong.
     */
    UNDEFINED("", "");

    /**
     * choice.
     */
    private String choice;
    /**
     * information about choice.
     */
    private String information;

    /**
     * @param pChoice      is a number of the menu item
     * @param pInformation is description of the menu item
     */
    UserMenuChoice(final String pChoice, final String pInformation) {
        this.choice = pChoice;
        this.information = pInformation;
    }

    /**
     * get choice.
     *
     * @return choice
     */
    public String getChoice() {
        return choice;
    }

    /**
     * get information about choice.
     *
     * @return information about choice
     */
    public String getInformation() {
        return information;
    }

    /**
     * @param userChoice is number of the menu item user chose
     * @return is the menu item user chose
     * @throws RuntimeException if there is not such menu item
     */
    public static UserMenuChoice get(final String userChoice)
            throws RuntimeException {
        for (UserMenuChoice choice : UserMenuChoice.values()) {
            if (choice.getChoice().equals(userChoice)) {
                return choice;
            }
        }
        return UserMenuChoice.UNDEFINED;
    }
}
