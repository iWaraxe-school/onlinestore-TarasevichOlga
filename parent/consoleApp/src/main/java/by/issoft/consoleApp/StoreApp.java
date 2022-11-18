package by.issoft.consoleApp;

import by.issoft.store.Multithreading.CreateOrderThread;
import by.issoft.store.Order;
import by.issoft.store.Store;
import by.issoft.store.helpers.RandomStorePopulator;
import by.issoft.store.Multithreading.CleanUpThread;
import by.issoft.store.StoreInteraction;

import by.issoft.store.helpers.comparators.ProductComparator;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Timer;

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

/*public class StoreApp {
    public static void main(String[] args) {

        Store store = Store.getInstance();
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(store);

        ProductComparator productComparator = new ProductComparator(store);
        randomStorePopulator.fillOutProductList();
        store.printCategoriesProducts();

        Timer timer = new Timer();
        timer.schedule( new CleanUpThread(), 0, 120000);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {

            boolean console = true;
            while (console) {

                System.out.println("Enter one of next commands: sort, top, order, quit:");
                String command = bufferedReader.readLine();
                System.out.println("Your command is: " + command);
                switch (command) {
                    case "sort":
                        productComparator.sortProducts(store);
                        break;
                    case "top":
                        productComparator.getTop5(store);
                        break;
                    case "order":
                        System.out.println("Order is created\n");
                        new CreateOrderThread(Order.getOrder());
                        break;
                    case "quit":
                        bufferedReader.close();
                        console = false;
                        break;
                    default:
                        System.out.println("Command is not supported.");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: the exception was thrown:" + e.getMessage());
        }
    }
}*/
