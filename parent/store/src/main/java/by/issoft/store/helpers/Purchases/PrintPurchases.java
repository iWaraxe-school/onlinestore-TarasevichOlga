package by.issoft.store.helpers.Purchases;

import by.issoft.store.HTTP.HTTPHelper;
import by.issoft.store.KindSorting;
import by.issoft.store.helpers.XMLparsers.SQLHelper;
import by.issoft.store.Store;

public class PrintPurchases extends Middleware  {

    Store store;
    SQLHelper sqlHelper = new SQLHelper();
    HTTPHelper httpHelper = new HTTPHelper();

    public PrintPurchases(Store store) {
        this.store = store;
    }

    public boolean check(String consoleCommand) {
        if (consoleCommand.toUpperCase().equals(CommandValues.PRINT_PURCHASES.toString())) {
//            store.printPurchaseCollection();
            //sqlHelper.selectFromPuchaseTable();
            httpHelper.httpClientHitEndpoint("/get-purchases");
            return false;
        }
        return checkNext(consoleCommand);
    }
}
