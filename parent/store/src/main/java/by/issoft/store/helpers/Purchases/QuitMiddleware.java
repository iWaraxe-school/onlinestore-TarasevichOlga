package by.issoft.store.helpers.Purchases;

import by.issoft.store.KindSorting;

public class QuitMiddleware extends Middleware{

    public boolean check(String consoleCommand) {
        if(consoleCommand.toUpperCase().equals(KindSorting.QUIT.toString())){
            System.out.println("!!!! EXIT !!!");
            return true;
        }
        return checkNext(consoleCommand);
    }

}