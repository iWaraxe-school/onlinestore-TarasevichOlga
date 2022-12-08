package by.issoft.store.helpers.Purchases;

import by.issoft.store.KindSorting;
import by.issoft.store.Multithreading.CreateOrderThread;
import by.issoft.store.HTTP.HTTPHelper;
import by.issoft.store.Store;

public class CreateOrderMiddleware extends Middleware{

    Store store;
    HTTPHelper httpHelper = new HTTPHelper();

    public CreateOrderMiddleware(Store store) {
        this.store = store;
    }

    public boolean check(String consoleCommand) {
        if(consoleCommand.toUpperCase().equals(CommandValues.CREATE_RANDOM_PURCHASE.toString())){
            //final CreateOrderThread createOrderThread = new CreateOrderThread(store);
            //new Thread(createOrderThread).start();
            httpHelper.httpClientHitEndpoint("/create-random-purchase");
            return false;
        }
        return checkNext(consoleCommand);
    }
}