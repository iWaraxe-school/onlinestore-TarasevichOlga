package by.issoft.store;

public enum KindSorting {
    SORT("sort"),
    TOP5("top");

    private final String value;

    KindSorting(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}