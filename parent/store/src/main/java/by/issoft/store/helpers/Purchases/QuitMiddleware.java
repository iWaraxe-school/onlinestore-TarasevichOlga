package by.issoft.store.helpers.Purchases;

import by.issoft.store.KindSorting;
import by.issoft.store.helpers.XMLparsers.SQLHelper;

public class QuitMiddleware extends Middleware{

    SQLHelper sqlHelper = new SQLHelper();

    public boolean check(String consoleCommand) {
        if(consoleCommand.toUpperCase().equals(CommandValues.QUIT.toString())){
            sqlHelper.closeSQLConnection();
            System.out.println("!!!! EXIT !!!");
            return true;
        }
        return checkNext(consoleCommand);
    }

}