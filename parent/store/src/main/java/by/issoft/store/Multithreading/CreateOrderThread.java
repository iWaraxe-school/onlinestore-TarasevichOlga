package by.issoft.store.Multithreading;

import by.issoft.domain.Product;
import by.issoft.store.Order;
import by.issoft.store.Store;
import by.issoft.store.helpers.XMLparsers.SQLHelper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class CreateOrderThread implements Runnable{

    static Order order;

    SQLHelper sqlHelper = new SQLHelper();

    public CreateOrderThread(Order order) {
        CreateOrderThread.order = order;
    }

    /*@SneakyThrows
    @Override
    public void run() {
        Product purchasedProduct = order.getRandomProductFromStore();
        System.out.println("Ordered product" + purchasedProduct);
        order.addPurchasedProduct(purchasedProduct);
        System.out.println(order.getListOfOrders());
        int i = (int) (1 + Math.random()*29);
        log.info("New good will be added to the purchases in " + i + " seconds.");
        TimeUnit.SECONDS.sleep(i);
        System.out.println("Ordered closed with purchased product:" + purchasedProduct);
    }*/

    @SneakyThrows
    @Override
    public void run() {
        int i = (int) (1 + Math.random()*29);
        log.info("New good will be added to the purchases in " + i + " seconds.");
        TimeUnit.SECONDS.sleep(i);
        sqlHelper.insertRandomProductIntoPurchaseTable();
    }

}
