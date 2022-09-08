package by.issoft.store.helpers.comparators;

import by.issoft.domain.Product;
import by.issoft.store.helpers.XMLparsers.XMLParser;

import java.util.Comparator;
import java.util.Map;
import java.util.Set;


public class ProductComparator implements Comparator<Product> {

    XMLParser parser = new XMLParser();
    final Set<Map.Entry<String, String>> entries = parser.getFieldSortOrderMap().entrySet();

    @Override
    public int compare(Product p1, Product p2) {
        int result = 0;
        for(Map.Entry<String, String> entry : entries){

            if(entry.getValue().equals("asc")){
                switch (entry.getKey()){
                    case "name": result = p1.getName().compareTo(p2.getName());
                        break;
                    case "rate": result = Double.compare(p1.getRate(), p2.getRate());
                        break;
                    case "price": result = Double.compare(p1.getPrice(), p2.getPrice());
                        break;
                }
            } else if(entry.getValue().equals("desc")){
                switch (entry.getKey()){
                    case "name": result = p2.getName().compareTo(p1.getName());
                        break;
                    case "rate": result = Double.compare(p2.getRate(), p1.getRate());
                        break;

                    case "price": result = Double.compare(p2.getPrice(), p1.getPrice());
                        break;
                }
            }

            if(result != 0) break;
        }
        return result;
    }
}
