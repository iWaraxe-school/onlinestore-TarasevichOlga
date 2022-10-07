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