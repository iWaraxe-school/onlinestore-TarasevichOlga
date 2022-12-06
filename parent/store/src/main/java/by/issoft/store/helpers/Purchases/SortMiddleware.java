package by.issoft.store.helpers.Purchases;

import by.issoft.store.KindSorting;
import by.issoft.store.helpers.XMLparsers.SQLHelper;
import by.issoft.store.Store;

public class SortMiddleware extends Middleware{

    Store store;
    SQLHelper sqlHelper = new SQLHelper();

    public SortMiddleware(Store store) {
        this.store = store;
    }

    public boolean check(String consoleCommand) {
        if(consoleCommand.toUpperCase().equals(KindSorting.SORT.toString())){
//            store.printSortProducts();
            sqlHelper.selectSortFromProductTable();
            return false;
        }
        return checkNext(consoleCommand);
    }

}