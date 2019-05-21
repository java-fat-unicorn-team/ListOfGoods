package com.spring.core.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * This class describes the structure of the product
 *
 * @author Katuranau Maksimilyan
 */

@Component
@Scope("prototype")
public class Product {
    private String name;
    private int price;
    private int weight;

    public Product() {
    }

    /**
     *
     * @param name is product's name
     * @param price is product's price
     * @param weight is product's weight
     */
    public Product(String name, int price, int weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * This method represents an object as a string
     *
     * @return String value
     */
    @Override
    public String toString() {
        return name + ": " + price + "$, " + weight + "g.";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return price == product.price &&
                weight == product.weight &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, weight);
    }
}
