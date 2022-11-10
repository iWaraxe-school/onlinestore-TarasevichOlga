package by.issoft.store.Multithreading;

import by.issoft.domain.Product;
import by.issoft.store.Order;
import by.issoft.store.Store;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class CreateOrderThread implements Runnable{

    static Order order;

    public CreateOrderThread(Order order) {
        CreateOrderThread.order = order;
    }

    @SneakyThrows
    @Override
    public void run() {
        int i = (int) (1 + Math.random()*29);
        log.info("New good will be added to the purchases in " + i + " seconds.");
        TimeUnit.SECONDS.sleep(i);
        Product purchasedProduct = order.getRandomProductFromStore();
        System.out.println("Ordered product" + purchasedProduct);
        order.addPurchasedProduct(purchasedProduct);
    }
}
