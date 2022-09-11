package by.issoft.domain;

public class Product {
    private String name;
    private double price;
    private double rate;

    public Product (String name, double price, double rate) {
        this.name = name;
        this.price = price;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public double getPrice() {
        return price;
    }
    public String toString() {
        return String.format("Name: %s, price: %.2f, rate: %.2f; %n", name, price, rate);
    }


}