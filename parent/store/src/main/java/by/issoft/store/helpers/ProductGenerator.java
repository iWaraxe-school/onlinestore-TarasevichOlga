package by.issoft.store.helpers;

import by.issoft.domain.Product;
import com.github.javafaker.Faker;

public class ProductGenerator {

        private Faker faker = new Faker();

        public String getProductName (String name) {
        switch (name) {
                case "Bike":
                        return faker.commerce().productName();
                case "Scooter":
                        return faker.esports().event();
                case "Motorbike":
                        return faker. lorem().fixedString(8);
                default:
                        return null;
        }
        }

        public double getPrice() {return Double.parseDouble(faker.commerce().price());
        }

        public double getRate() {return faker.random().nextDouble();
        }

}





