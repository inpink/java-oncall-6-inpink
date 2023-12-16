package oncall.service;

import java.util.LinkedHashMap;
import java.util.Map;
import oncall.domain.date.Dates;
import oncall.domain.date.DayOfWeek;
import oncall.domain.date.Month;
import oncall.domain.date.Orders;
import oncall.domain.date.StartDate;

public class OncallService {
    public Dates generateDates(StartDate startDate) {
        Month month = startDate.getMonth();
        DayOfWeek startDay = startDate.getDay();
        Orders orders = Orders.create(startDay);

        Map<Integer, DayOfWeek> days = new LinkedHashMap<>();

        final int lastDayNumber = month.getLastDay();

        for (int dayNumber = 1; dayNumber <= lastDayNumber; dayNumber++) {
            DayOfWeek day = DayOfWeek.MONDAY;
            days.put(dayNumber, day);
        }

        return Dates.create(month, days);
    }
}
