package oncall.constants;

public enum IntegerConstants {

    MONTH_AND_DAY_COUNT(2),
    DAY_ORDER_FIRST_NUMBER(1),
    DAY_ORDER_LAST_NUMBER(7);

    private final int value;

    IntegerConstants(final int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }
}
