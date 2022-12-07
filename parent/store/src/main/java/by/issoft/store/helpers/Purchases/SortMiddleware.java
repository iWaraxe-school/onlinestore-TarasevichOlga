package by.issoft.store.helpers.Purchases;

import by.issoft.store.HTTP.HTTPHelper;
import by.issoft.store.KindSorting;
import by.issoft.store.helpers.XMLparsers.SQLHelper;
import by.issoft.store.Store;

public class SortMiddleware extends Middleware{

    Store store;
    SQLHelper sqlHelper = new SQLHelper();
    HTTPHelper httpHelper = new HTTPHelper();

    public SortMiddleware(Store store) {
        this.store = store;
    }

    public boolean check(String consoleCommand) {
        if(consoleCommand.toUpperCase().equals(CommandValues.SORT.toString())){
//            store.printSortProducts();
            //sqlHelper.selectSortFromProductTable();
            httpHelper.httpClientHitEndpoint("/get-sort-products");
            return false;
        }
        return checkNext(consoleCommand);
    }

}