package com.spring.core.model;

import java.math.BigDecimal;

/**
 * This class describes the structure of the product.
 *
 * @author Katuranau Maksimilyan
 */
public class Product {
    /**
     * name of product.
     */
    private String name;
    /**
     * price of product.
     */
    private BigDecimal price;
    /**
     * weight of product.
     */
    private int weight;

    /**
     * default constructor.
     */
    public Product() {
    }

    /**
     * @param pName   is product's name
     * @param pPrice  is product's price
     * @param pWeight is product's weight
     */
    public Product(final String pName, final BigDecimal pPrice,
                   final int pWeight) {
        this.name = pName;
        this.price = pPrice;
        this.weight = pWeight;
    }

    /**
     * @return name
     */
    public final String getName() {
        return name;
    }

    /**
     * @param pName name of product
     */
    public final void setName(final String pName) {
        this.name = pName;
    }

    /**
     * @return price
     */
    public final BigDecimal getPrice() {
        return price;
    }

    /**
     * @param pPrice price of product
     */
    public final void setPrice(final BigDecimal pPrice) {
        this.price = pPrice;
    }

    /**
     * @return weight
     */
    public final int getWeight() {
        return weight;
    }

    /**
     * @param pWeight weight of product
     */
    public final void setWeight(final int pWeight) {
        this.weight = pWeight;
    }

    /**
     * This method represents an object as a string.
     *
     * @return String value
     */
    @Override
    public final String toString() {
        return name + ": " + price + "$, " + weight + "g.";
    }
}
