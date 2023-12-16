package oncall.domain.date;

import java.util.Arrays;
import oncall.util.ExceptionUtil;

public enum DayOfWeek {
    SUNDAY("일"),
    MONDAY("월"),
    TUESDAY("화"),
    WEDNESDAY("수"),
    THURSDAY("목"),
    FRIDAY("금"),
    SATURDAY("토");

    private final String koreanLabel;

    DayOfWeek(String koreanLabel) {
        this.koreanLabel = koreanLabel;
    }

    public static DayOfWeek findDay(String dayLabel) {
        return Arrays.stream(DayOfWeek.values())
                .filter(day -> day.koreanLabel.equals(dayLabel))
                .findAny()
                .orElseThrow(() -> ExceptionUtil.returnInvalidValueException());
    }



    public String getKoreanLabel() {
        return koreanLabel;
    }
}

