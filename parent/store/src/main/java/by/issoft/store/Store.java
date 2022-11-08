package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;


import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;


public class Store {

    private List<Category> categoryList = new ArrayList<>();
    private CopyOnWriteArrayList<Category> purchasedCategory = new CopyOnWriteArrayList<>();


    // Singleton pattern
    private Store () {
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

    public List<Product> getAllProducts() {
        return Store.getInstance().getCategoriesList().stream().map(Category::getProductList).
                flatMap(Collection::stream).collect(Collectors.toList());
    }

    public void setProductCategoryList(Category category) {
        this.categoryList.add(category);
    }

    public void printProduct(List<Product> products){
        for(Product productList : products){
            System.out.println(productList.getName() + " " + productList.getPrice() + " " + productList.getRate());
        }
    }

    public void printCategoriesProducts() {
        for (Category category : categoryList) {
            category.printProductList();
        }
    }

    // Purchase's methods

    public void addRandomPurchaseGood (List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Store [");
        for (Category category : categoryList) {
            sb.append("\n");
            sb.append(category.toString());
        }
        return sb.append("\n]").toString();
    }

    public void putToOrder(Category category) {
        purchasedCategory.add(category);
    }

    public void printPurchaseCollection(){
        System.out.println("Size of purchases is " + this.purchasedCategory.size());
        for(Category outputList : this.purchasedCategory) {
            System.out.println("Category = " + outputList.getName());
            printProduct(outputList.getProductList());
        }
    }

    public void deleteFromOrderList(){
        this.purchasedCategory.clear();
    }

}
