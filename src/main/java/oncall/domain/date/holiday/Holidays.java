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


    public static boolean isHoliday(final Month month,
                                    final int dayNumber,
                                    final DayOfWeek date) {
        return isBasicHoliday(date)
                || isLegalHoliday(month, dayNumber);
    }

    public static boolean isBasicHoliday(final DayOfWeek date) {
        return Stream.of(BasicHoliday.values())
                .anyMatch(holiday ->
                        isSameDay(date, holiday));
    }

    public static boolean isLegalHoliday(final Month month, final int dayNumber) {
        return Stream.of(LegalHoliday.values())
                .anyMatch(holiday -> isSameDate(month, dayNumber, holiday));
    }

    private static boolean isSameDay(DayOfWeek date, BasicHoliday holiday) {
        return holiday.getKoreanDescription().equalsIgnoreCase(date.getKoreanDescription());
    }

    private static boolean isSameDate(final Month month,
                                      final int dayNumber,
                                      final LegalHoliday holiday) {
        return holiday.getMonth() == month.getMonthNumber()
                && holiday.getDay() == dayNumber;
    }
}
