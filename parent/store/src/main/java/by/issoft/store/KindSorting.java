package by.issoft.store;

public enum KindSorting {

    SORT("sort"),
    TOP5("top"),
    PRINT_PURCHASES ("purchases"),
    CREATE_ORDER ("order"),
    QUIT ("quit");

  private final String value;

    KindSorting(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}