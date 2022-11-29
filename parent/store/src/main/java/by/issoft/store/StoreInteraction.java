package by.issoft.store;

import by.issoft.store.Multithreading.CreateOrderThread;
import by.issoft.store.helpers.XMLparsers.SQLHelper;
import by.issoft.store.helpers.comparators.ProductComparator;
import by.issoft.store.helpers.RandomStorePopulator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StoreInteraction {

    public static void storeInteraction(Store store) {

        ProductComparator productComparator = new ProductComparator(store);
        RandomStorePopulator randomStorePopulator = new RandomStorePopulator(store);

        SQLHelper sqlHelper = new SQLHelper();

        randomStorePopulator.fillOutProductList();

        try {

            boolean console = true;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("The store is created and filled with random products.");

            while (console) {

                System.out.println("The store interacts with using next commands: sort, top, order, quit:");
                String command = bufferedReader.readLine();
                System.out.println("Your command is: " + command);
                switch (command) {
                    case "sort":
                        sqlHelper.selectSortFromProductTable();
                        break;
                    case "top":
                        sqlHelper.selectTop5FromProductTable();
                        break;
                    case "order":
                        System.out.println("Order is created\n");
                        Runnable createOrderThread = new CreateOrderThread(Order.getOrder());
                        new Thread(createOrderThread).start();
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




