package by.issoft.store.helpers.Purchases;

import by.issoft.store.KindSorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class UnknownMiddleware extends Middleware{

    List commandvalues = Collections.singletonList(Arrays.toString(KindSorting.values()));

    public boolean check(String consoleCommand) {

        if (!commandvalues.contains(consoleCommand.toUpperCase())) {
            System.out.println("Oooops, somthenig went wrong. Try again.");
            return false;
        }
        return checkNext(consoleCommand);
    }
}