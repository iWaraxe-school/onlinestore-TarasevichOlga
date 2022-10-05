package by.issoft.consoleApp;

import by.issoft.store.Store;
import by.issoft.store.helpers.RandomStorePopulator;
import by.issoft.store.helpers.comparators.ProductComparator;

import java.util.Map;


public class StoreApp {
    public static void main(String[] args) {
        Store onlineStore = new Store();
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(onlineStore);
        randomStorePopulator.fillInStore();
        onlineStore.printCategoriesProducts();

        ProductComparator productComparator = new ProductComparator(onlineStore);


        productComparator.getTop5(onlineStore);

        try {
            productComparator.sortProducts(onlineStore);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

   


