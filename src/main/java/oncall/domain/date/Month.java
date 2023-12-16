package oncall.domain.date;

import java.util.Arrays;
import oncall.util.ExceptionUtil;

public enum Month {
    JANUARY(1, 31),
    FEBRUARY(2, 28),
    MARCH(3, 31),
    APRIL(4, 30),
    MAY(5, 31),
    JUNE(6, 30),
    JULY(7, 31),
    AUGUST(8, 31),
    SEPTEMBER(9, 30),
    OCTOBER(10, 31),
    NOVEMBER(11, 30),
    DECEMBER(12, 31);

    private static final String suffix = "월";
    private final int monthNumber;
    private final int days;


    Month(int monthNumber, int days) {
        this.monthNumber = monthNumber;
        this.days = days;
    }

    public static Month findMonth(int month) {
        return Arrays.stream(Month.values())
                .filter(monthNumber -> monthNumber.monthNumber == month)
                .findAny()
                .orElseThrow(() -> ExceptionUtil.returnInvalidValueException());
    }

    public String getKoreanLabel() {
        return monthNumber + suffix;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public int getDays() {
        return days;
    }
}
