package oncall.domain;


public class StartDate {

    private final Month month;
    private final DayOfWeek day;

    public StartDate(int month, String day) {

        this.month = toMonth(month);
        this.day = toDay(day);
    }

    public static StartDate create(int month, String day) {
        return new StartDate(month, day);
    }

    private Month toMonth(int month) {
        return Month.findMonth(month);
    }

    private DayOfWeek toDay(String day) {
        return DayOfWeek.findDay(day);
    }

    public Month getMonth() {
        return month;
    }

    public DayOfWeek getDay() {
        return day;
    }
}
