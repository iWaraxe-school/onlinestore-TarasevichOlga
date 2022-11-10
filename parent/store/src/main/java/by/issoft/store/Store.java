package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.helpers.comparators.ProductComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.stream.Collectors;

public class Store {

    private List<Category> categoryList = new ArrayList<>();

    // Singleton pattern
    private Store () {
    }

    static class SingletoneHelper{
        private static final Store PURCHASED_PRODUCTS_STORAGE = new Store();
    }

    public static Store getInstance() {
        return Store.SingletoneHelper.PURCHASED_PRODUCTS_STORAGE;
    }

    public void addCategory(Category category) {
        categoryList.add(category);
    }
    public List<Category> getCategoriesList() {
        return categoryList;
    }

    @Override
    public String toString() {
        return "Store with categories: " +
                categoryList;
    }

    public List<Product> getAllProducts() {
        return Store.getInstance().getCategoriesList().stream()
                .map(Category::getProductList).
                flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public void printCategoriesProducts() {
        for (Category category : categoryList) {
            category.printProductList();
        }
    }
}
