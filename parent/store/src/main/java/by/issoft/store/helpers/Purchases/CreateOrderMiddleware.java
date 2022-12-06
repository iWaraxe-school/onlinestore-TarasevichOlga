package by.issoft.store.helpers.Purchases;

import by.issoft.store.KindSorting;
import by.issoft.store.Multithreading.CreateOrderThread;
import by.issoft.store.Store;

public class CreateOrderMiddleware extends Middleware{

    Store store;

    public CreateOrderMiddleware(Store store) {
        this.store = store;
    }

    public boolean check(String consoleCommand) {
        if(consoleCommand.toUpperCase().equals(KindSorting.CREATE_ORDER.toString())){
            final CreateOrderThread createOrderThread = new CreateOrderThread(store);
            new Thread(createOrderThread).start();
            return false;
        }
        return checkNext(consoleCommand);
    }
}