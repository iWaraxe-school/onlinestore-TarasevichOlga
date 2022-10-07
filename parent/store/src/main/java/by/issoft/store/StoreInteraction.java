package by.issoft.store;

import by.issoft.store.helpers.comparators.ProductComparator;
import by.issoft.store.helpers.RandomStorePopulator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StoreInteraction {

    public static void storeInteraction(Store store) {

        ProductComparator productComparator = new ProductComparator(store);
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(store);
        randomStorePopulator.fillInStore();

        try {

            boolean console = true;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("The store is created and filled with random products.");

            while (console) {

                System.out.println("The store interacts with using next commands: sort, top, quit/q:");
                String command = bufferedReader.readLine();

                switch (command) {
                    case "sort":
                        productComparator.sortProducts(store);
                        break;
                    case "top":
                        productComparator.getTop5(store);
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
}