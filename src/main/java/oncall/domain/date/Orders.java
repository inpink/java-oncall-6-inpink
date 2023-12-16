package oncall.domain.date;

import java.util.LinkedHashMap;
import java.util.Map;

public class Orders {
    private final Map<DayOfWeek, Integer> orders;

    private Orders(DayOfWeek startDay) {
        this.orders = toOrders(startDay);
    }

    public static Orders create(DayOfWeek startDay) {
        return new Orders(startDay);
    }

    private Map<DayOfWeek, Integer> toOrders(DayOfWeek startDay) {
        Map<DayOfWeek, Integer> orders = new LinkedHashMap<>();
        DayOfWeek[] days = DayOfWeek.values();
        int startDayIndex = startDay.ordinal();

        for (int i = 0; i < days.length; i++) {
            int order = (startDayIndex + i) % days.length;
            orders.put(days[order], i + 1);
        }

        return orders;
    }

    public Map<DayOfWeek, Integer> getOrders() {
        return orders;
    }
}
