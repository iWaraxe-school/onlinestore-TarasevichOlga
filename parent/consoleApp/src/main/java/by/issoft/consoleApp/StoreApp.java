package by.issoft.consoleApp;

import by.issoft.store.Store;
import by.issoft.store.helpers.RandomStorePopulator;

public class StoreApp {
    public static void main(String[] args) {
        Store onlineStore = new Store();
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(onlineStore);
        randomStorePopulator.fillInStore();
        onlineStore.printCategoriesProducts();
    }
}

