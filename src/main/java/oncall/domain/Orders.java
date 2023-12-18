package oncall.domain;

import java.util.LinkedHashMap;
import java.util.Map;
import oncall.domain.date.DayOfWeek;

public class Orders {
    private final Map<Integer, DayOfWeek> orders;

    private Orders(DayOfWeek startDay) {
        this.orders = toOrders(startDay);
    }

    public static Orders create(DayOfWeek startDay) {
        return new Orders(startDay);
    }

    public DayOfWeek findDay(int dayNumber) {
        return orders.get(dayNumber);
    }

    private Map<Integer, DayOfWeek> toOrders(DayOfWeek startDay) {
        Map<Integer, DayOfWeek> orders = new LinkedHashMap<>();
        DayOfWeek[] days = DayOfWeek.values();
        int startDayIndex = startDay.ordinal();

        for (int i = 0; i < days.length; i++) {
            int order = (startDayIndex + i) % days.length;
            orders.put(i, days[order]);
        }

        return orders;
    }

    public Map<Integer, DayOfWeek> getOrders() {
        return orders;
    }

}
