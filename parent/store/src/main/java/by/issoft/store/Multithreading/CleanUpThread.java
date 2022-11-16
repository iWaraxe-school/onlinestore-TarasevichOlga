package by.issoft.store.Multithreading;

/*import by.issoft.store.Order;
import by.issoft.store.Store;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CleanUpThread extends TimerTask {

    private final Store store = Store.getInstance();
    private final Order purchaseProductStorage = Order.getInstance();

    @Override
    public void run() {
            System.out.println("Theread name:" + Thread.currentThread().getName());
            System.out.println ("Order list was cleared");
            purchaseProductStorage.deleteFromOrderList();

            purchaseProductStorage.printOrderedProducts();
        }
}*/


import by.issoft.store.Order;
import by.issoft.store.Store;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class CleanUpThread implements Runnable {

    static Order order;
    public boolean needRun = true;

    public CleanUpThread(Order order) {
        CleanUpThread.order = order;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (needRun) {
            TimeUnit.MINUTES.sleep(2);
            log.info("The purchased collection was clean up.");
            order.deleteFromOrderList();

            order.printOrderedProducts();
        }
    }

    public void finish() {
        log.info("The purchased collection will be clean up for some minutes, and application will be stopped.");
        needRun = false;
    }
}



