package oncall.domain;

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
    private final String koreanLabel;
    private final int days;


    Month(int monthNumber, int days) {
        this.koreanLabel = monthNumber + suffix;
        this.days = days;
    }

    public String getKoreanLabel() {
        return koreanLabel;
    }

    public int getDays() {
        return days;
    }
}
