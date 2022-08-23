package by.issoft.store.helpers;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import org.reflections.Reflections;
import org.reflections.Store;


import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RandomStorePopulator {
    Store store;

    public RandomStorePopulator(Store store) {
        this.store = store;
    }

    public void FillInStore() {
        ProductGenerator populator = new ProductGenerator();
        Set<Category> categorySet = createCategorySet();


        for (Category category : categorySet) {
            Random random = new Random();
            for (int i = 0; i < random.nextInt(10) +1; i++) {
                Product product = new Product(
                        populator.getProductName(category.getName()),
                        populator.getPrice(),
                        populator.getRate());
                category.addProductList(product);
            }

        }

    }

    private Set<Category> createCategorySet() {
        Set<Category> productsToAdd = new HashSet<>();


        return productsToAdd;
    }

}



