package oncall.constants;

public enum IntegerConstants {

    MONTH_AND_DAY_COUNT(2);

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
