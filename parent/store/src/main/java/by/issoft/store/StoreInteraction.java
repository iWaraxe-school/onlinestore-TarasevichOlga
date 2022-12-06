package by.issoft.store;

import by.issoft.store.helpers.Purchases.*;


import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class StoreInteraction {

    static Store store;
    private static MiddlewareServer middlewareServer;

    public StoreInteraction(Store store) {
        StoreInteraction.store = store;
    }

    public String InputString() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    private static void initMiddleware() {
        middlewareServer = new MiddlewareServer();

        Middleware middleware = new SortMiddleware(store);
        middleware.linkWith(new TopMiddleware(store))
                .linkWith(new CreateOrderMiddleware(store))
                .linkWith(new PrintPurchases(store))
                .linkWith(new QuitMiddleware())
                .linkWith(new UnknownMiddleware());

        middlewareServer.setMiddleware(middleware);
    }

    @SneakyThrows
    public void ConsoleInteraction() {
        initMiddleware();
        boolean isQuit = false;

        while(!isQuit){
            TimeUnit.MILLISECONDS.sleep(300);
            System.out.print("Enter command (sort, top5, create_order, print_purchases, quit): ");
            isQuit = middlewareServer.processingMiddleware(InputString());
        }
    }

   /* public static void storeInteraction(Store store) {

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
    }*/

}




