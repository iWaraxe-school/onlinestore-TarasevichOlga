package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.CategoryFactory;
import by.issoft.domain.CategoryType;
import by.issoft.domain.Product;
import by.issoft.store.helpers.ProductGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;


public class Store extends org.reflections.Store {
    private List<Category> categoryList;
    private CopyOnWriteArrayList<Category> purchasedCategory;

    public Store() {
        this.categoryList = new ArrayList<>();
        this.purchasedCategory = new CopyOnWriteArrayList<>();
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

    public void printCategoriesProducts() {
        for (Category category : categoryList) {
            category.printProductList();
        }
    }

    public void printProduct(List<Product> products){
        for(Product productList : products){
            System.out.println(productList.getName() + " " + productList.getPrice() + " " + productList.getRate());
        }
    }

    // Purchase's methods

    public Category createRandomPurchaseGood(){
        ProductGenerator productGenerator = new ProductGenerator();
        Category category = new CategoryFactory().getCategory(CategoryType.randomDirection());
        Product product = new Product(
                productGenerator.getProductName(category.getName()),
                productGenerator.getRate(),
                productGenerator.getPrice()
        );
        category.addProductList(product);
        return category;
    }

    public void putToOrder(){
        this.purchasedCategory.add(createRandomPurchaseGood());
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