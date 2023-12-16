package oncall.domain.holiday;

public enum BasicHoliday implements Holiday {
    SATURDAY("토요일"),
    SUNDAY("일요일");

    private final String koreanDescription;

    BasicHoliday(String koreanDescription) {
        this.koreanDescription = koreanDescription;
    }

    public String getKoreanDescription() {
        return koreanDescription;
    }


}
