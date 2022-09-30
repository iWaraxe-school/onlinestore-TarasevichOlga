package by.issoft.store.helpers.comparators;


import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import by.issoft.store.helpers.XMLparsers.XMLParser;

import java.util.*;


public class ProductComparator {
    // creating Map of Comparators
    private static final Map<String, Comparator<Product>> COMPARATOR_MAP = new LinkedHashMap<String, Comparator<Product>>() {{
        // method reference
        put("name", Comparator.comparing(Product::getName));
        // lambda body instead of method reference
        put("price", Comparator.comparing(Product::getPrice));
        // method reference
        put("rate", Comparator.comparing(Product::getRate));
    }};

    Store store;

    public ProductComparator(Store store) {
        this.store = store;
    }

    private Comparator<Product> chooseComparator(Map.Entry<String, String> entry) {
        String comparatorName = entry.getKey();
        Comparator<Product> comparator = COMPARATOR_MAP.getOrDefault(comparatorName, null);

        if (entry.getValue().equals(TypesOfSorting.DESC.toString()) && Objects.nonNull(comparator)) {
            comparator = comparator.reversed();
        }
        return comparator;
    }

    private Comparator<Product> buildComparator(Map<String, String> comparatorConfig) throws Exception {
        return comparatorConfig.entrySet().stream()
                .map(this::chooseComparator)
                .filter(Objects::nonNull)
                .reduce(Comparator::thenComparing)
                .orElseThrow(Exception::new);
    }

    public void sortProducts(Store store) throws Exception {
        XMLParser parser = new XMLParser();
        Map<String, String> configMap = parser.getFieldSortOrderMap();
        Comparator<Product> productComparator = buildComparator(configMap);

        System.out.println("-----------------------------------------------");
        System.out.println("SORT PRODUCTS - COMPARATOR FROM STREAM SOLUTION");
        System.out.println("-----------------------------------------------");

        store.getCategoriesList().stream()
                .map(Category::getProductList)
                .flatMap(Collection::stream)
                .sorted(productComparator)
                .forEach(System.out::println);
    }

    public void getTop5(Store store) {
        System.out.println("----------------------------");
        System.out.println("TOP 5");
        System.out.println("----------------------------");

        store.getCategoriesList().stream()
                .map(Category::getProductList)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(5)
                .forEach(System.out::println);
    }
}