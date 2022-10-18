package by.issoft.store;

import by.issoft.domain.Category;

import by.issoft.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class Store extends org.reflections.Store {
    private List<Category> categoryList;

    public Store() {
        this.categoryList = new ArrayList<>();
    }

    static class SingletoneHelper{
        private static final Store UNIQUE_INSTANCE = new Store();
    }

    public static Store getInstance() {
        return SingletoneHelper.UNIQUE_INSTANCE;
    }

    public void addCategory(Category category) {
        categoryList.add(category);
    }
    public List<Category> getCategoriesList() {
        return categoryList;
    }

    public void printCategoriesProducts() {
        for (Category category : categoryList) {
            category.printProductList();
        }
    }

}