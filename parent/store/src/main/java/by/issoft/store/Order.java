package by.issoft.store;

import by.issoft.domain.Product;

import lombok.ToString;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

@ToString
public class Order {
    private Store store = Store.getInstance();
    private static Order order = null;
    private List<Product> productList = store.getAllProducts();
    private List<Product> listOfOrders = new CopyOnWriteArrayList<>();

    private Order() {
    }

    public static Order getOrder() {
        if (order == null) {
            order = new Order();
        }
        return order;
    }

    public List<Product> getListOfOrders() {
        return listOfOrders;
    }

    public synchronized void putToOrder() {
        Random rand = new Random();
        int randomIndex = rand.nextInt(productList.size() - 1);
        Product randomElement = productList.get(randomIndex);
        listOfOrders.add(randomElement);
        productList.remove(randomIndex);
        System.out.println("Remove from product list to orders that product: " + randomElement);
        System.out.println("OrderList: " + getListOfOrders());
    }

    public synchronized void deleteFromOrderList() {
        System.out.println("OrderList cleared");
        listOfOrders.clear();
        System.out.println("OrderList: " + getListOfOrders());
    }
}
