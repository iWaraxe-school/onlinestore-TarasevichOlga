package by.issoft.store.helpers.Purchases;

import by.issoft.store.KindSorting;
import by.issoft.store.helpers.XMLparsers.SQLHelper;
import by.issoft.store.Store;

public class TopMiddleware extends Middleware{

    Store store;
    SQLHelper sqlHelper = new SQLHelper();

    public TopMiddleware(Store store) {
        this.store = store;
    }

    public boolean check(String consoleCommand) {
        if(consoleCommand.toUpperCase().equals(KindSorting.TOP5.toString())){
//            store.printTopProducts();
            sqlHelper.selectTop5FromProductTable();
            return false;
        }
        return checkNext(consoleCommand);
    }

}