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
    private List<Product> productList ;
    private static List<Product> listOfOrders = new CopyOnWriteArrayList<>();

    static class SingletoneHelper{
        private static final Order PURCHASED_PRODUCTS_STORAGE = new Order();
    }

    public static Order getInstance() {
        return Order.SingletoneHelper.PURCHASED_PRODUCTS_STORAGE;
    }

    private Order() {
        productList = store.getAllProducts();
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

    public Product getRandomProductFromStore () {
        Random random = new Random();
        List<Product> allProducts = Store.getInstance().getAllProducts();
        return allProducts.get(random.nextInt(allProducts.size()));
    }

    public void addPurchasedProduct (Product product) {listOfOrders.add(product);}

    public synchronized void deleteFromOrderList() {
        System.out.println("OrderList cleared");
        listOfOrders.clear();
        System.out.println("OrderList: " + getListOfOrders());
    }

    public void printOrderedProducts(){
        System.out.println("*******************************************************************");
        System.out.println("You have ordered following products: ");
        System.out.println("___________________________________________________________________");
        listOfOrders.forEach(System.out::println);
        System.out.println("___________________________________________________________________\n");

    }
}
