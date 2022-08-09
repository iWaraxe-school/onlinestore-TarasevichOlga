package by.issoft.domain;

import java.util.ArrayList;
import java.util.List;

public class Category {
    private static String name;
    private List<Product> productList;

    public Category(String name) {
        this.name = name;
        this.productList = new ArrayList<>();
    }

    public static String getName() {
        return name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void addProductList(Product product) {
        this.productList.add(product);
    }


    public void printProductList() {
        System.out.println(String.format("%s", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"));
        System.out.println("Category: " + name + ".");
        System.out.println(String.format("%s", "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"));
        for (Product product : productList) {
            System.out.println(product.toString());
        }
    }
}



