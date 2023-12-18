package oncall.view;

import static oncall.messages.IOMessages.HOLIDAY;

import java.util.List;
import java.util.Map;
import oncall.domain.date.DayOfWeek;
import oncall.domain.date.Month;
import oncall.domain.date.holiday.Holidays;
import oncall.domain.dto.OncallDto;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void outputOncallResult(OncallDto oncallDto) {
        int monthNumber = oncallDto.monthNumber();
        Map<Integer, DayOfWeek> days = oncallDto.days();
        List<String> employees = oncallDto.employees();

        StringBuilder output = buildResultFormat(days, employees, monthNumber);

        System.out.print(output.toString());
    }

    public void outputErrorMessage(String message) {
        System.out.println(message);
    }

    private static StringBuilder buildResultFormat(Map<Integer, DayOfWeek> days,
                                                   List<String> employees,
                                                   int monthNumber) {
        StringBuilder output = new StringBuilder();
        int employeeIndex = 0;
        int lastEntryIndex = days.size();

        for (Map.Entry<Integer, DayOfWeek> entry : days.entrySet()) {
            int dayNumber = entry.getKey();
            DayOfWeek dayOfWeek = entry.getValue();
            String employeeName = employees.get(employeeIndex++);

            output.append(formatDateAndEmployee(monthNumber, dayNumber, dayOfWeek, employeeName));
            appendLineSeparator(employeeIndex, lastEntryIndex, output);
        }
        return output;
    }

    private static void appendLineSeparator(int employeeIndex, int lastEntryIndex, StringBuilder output) {
        if (employeeIndex < lastEntryIndex) {
            output.append(LINE_SEPARATOR);
        }
    }

    private static String formatDateAndEmployee(final int monthNumber,
                                                final int dayNumber,
                                                final DayOfWeek dayOfWeek,
                                                final String employeeName) {
        final String koreanDayLabel = dayOfWeek.getKoreanLabel();
        return String.format("%d월 %d일 %s%s %s",
                monthNumber,
                dayNumber,
                koreanDayLabel,
                formatHolidayAndWeekday(monthNumber, dayNumber, koreanDayLabel),
                employeeName);
    }

    private static String formatHolidayAndWeekday(final int monthNumber,
                                                  final int dayNumber,
                                                  final String koreanDayLabel) {
        if (!Holidays.isBasicHoliday(DayOfWeek.findDay(koreanDayLabel))
                && Holidays.isLegalHoliday(Month.findMonth(monthNumber), dayNumber)) {
            return "(" + HOLIDAY.getMessage() + ")";
        }
        return "";
    }
}