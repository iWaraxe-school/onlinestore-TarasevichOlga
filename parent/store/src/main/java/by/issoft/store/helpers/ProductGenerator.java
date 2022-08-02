package by.issoft.store.helpers;

import by.issoft.domain.Product;
import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;

public class ProductGenerator {


    public static List<Product> getProductList (int numberProducts) {
        List<Product> products = new ArrayList<>(numberProducts);
        Faker faker = new Faker();

        String name = faker.commerce().productName();
        double price = Double.parseDouble(faker.commerce().price());
        double rate = faker.random().nextDouble();

        return products;

}




}



