package oncall.domain;


import java.time.Month;

public class StartDate {

    private final Month month;
    private final DayOfWeek day;

    public StartDate(int month, String day) {
        this.month = month;
        this.day = day;
    }

    public static StartDate create(int month, String day) {

    }
}
