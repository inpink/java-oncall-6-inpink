package oncall.domain.date;

import java.util.Map;
import java.util.Set;

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

    public Month getMonth() {
        return month;
    }

    public DayOfWeek getDay(int dayNumber) {
        return days.get(dayNumber);
    }

    public Map<Integer, DayOfWeek> getDays() {
        return days;
    }

    public Set<Integer> getDayNumbers() {
        return days.keySet();
    }

}
