package oncall.domain.date;


import java.util.Map;

public class Dates {
    private final Month month;
    private final Map<Integer, DayOfWeek> days;

    private Dates(final Month month, final Map<Integer, DayOfWeek> days) {
        this.month = month;
        this.days = days;
    }

    public static Dates create(Month month, Map<Integer, DayOfWeek> days) {
        return new Dates(month, days);
    }

    //TODO: integer받아서 is휴일인가 boolean 반환


    public Month getMonth() {
        return month;
    }

    public Map<Integer, DayOfWeek> getDays() {
        return days;
    }
}
