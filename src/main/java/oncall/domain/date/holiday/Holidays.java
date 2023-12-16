package oncall.domain.date.holiday;

import java.util.Arrays;
import java.util.List;

public enum Holidays {

    BASIC_HOLIDAY(BasicHoliday.values()),
    LEGAL_HOLIDAY(LegalHoliday.values());

    private final List<Holiday> holidays;

    Holidays(Holiday[] holidays) {
        this.holidays = Arrays.stream(holidays).toList();
    }


}
