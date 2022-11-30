package by.issoft.consoleApp;


import by.issoft.store.Order;
import by.issoft.store.Store;
import by.issoft.store.helpers.RandomStorePopulator;
import by.issoft.store.Multithreading.CleanUpThread;
import by.issoft.store.StoreInteraction;


import lombok.SneakyThrows;


public class StoreApp {
    @SneakyThrows
    public static void main(String[] args) {

        Store store = Store.getInstance();

        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(store);
        StoreInteraction storeInteraction = new StoreInteraction();

        randomStorePopulator.fillInStore();
        store.printCategoriesProducts();

        final CleanUpThread cleanUpThread = new CleanUpThread(Order.getOrder());
        new Thread(cleanUpThread).start();

        storeInteraction.storeInteraction(store);
        cleanUpThread.finish();
    }
}