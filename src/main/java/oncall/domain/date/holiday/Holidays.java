package oncall.domain.date.holiday;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import oncall.domain.date.DayOfWeek;
import oncall.domain.date.Month;

public enum Holidays {

    BASIC_HOLIDAY(BasicHoliday.values()),
    LEGAL_HOLIDAY(LegalHoliday.values());

    private final List<Holiday> holidays;

    Holidays(Holiday[] holidays) {
        this.holidays = Arrays.stream(holidays).toList();
    }


    public static boolean isHoliday(Month month, int dayNumber, DayOfWeek date) {
        return isBasicHoliday(date)
                || isLegalHoliday(month, dayNumber);
    }

    public static boolean isBasicHoliday(DayOfWeek date) {
        return Stream.of(BasicHoliday.values())
                .anyMatch(holiday -> holiday.getKoreanDescription().equalsIgnoreCase(date.getKoreanDescription()));
    }

    public static boolean isLegalHoliday(Month month, int dayNumber) {
        return Stream.of(LegalHoliday.values())
                .anyMatch(holiday -> holiday.getMonth() == month.getMonthNumber()
                        && holiday.getDay() == dayNumber);
    }
}
