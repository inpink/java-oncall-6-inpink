package oncall.domain.date;


import java.util.Map;

public class Dates {
    private final Month month;
    private final Map<Integer, DayOfWeek> day;

    private Dates(Month month, Map<Integer, DayOfWeek> day) {
        this.month = month;
        this.day = day;
    }

    public static Dates create(Month month, Map<Integer, DayOfWeek> days) {
        return new Dates(month, days);
    }

    //TODO: integer받아서 is휴일인가 boolean 반환
}
