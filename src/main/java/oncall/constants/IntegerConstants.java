package oncall.constants;

public enum IntegerConstants {

    MONTH_AND_DAY_COUNT(2),
    DAY_A_WEEK_SIZE(7),
    MIN_NICKNAME_LENGTH(1),
    MAX_NICKNAME_LENGTH(5),
    MIN_EMPLOYEES_COUNT(5),
    MAX_EMPLOYEES_COUNT(35);

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
