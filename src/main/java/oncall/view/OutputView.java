package oncall.view;

import java.util.List;
import java.util.Map;
import oncall.domain.date.DayOfWeek;
import oncall.domain.dto.OncallDto;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    private static StringBuilder buildResultFormat(Map<Integer, DayOfWeek> days,
                                                   List<String> employees,
                                                   int monthNumber) {
        StringBuilder output = new StringBuilder();
        int employeeIndex = 0;

        for (Map.Entry<Integer, DayOfWeek> entry : days.entrySet()) {
            int dayNumber = entry.getKey();
            DayOfWeek dayOfWeek = entry.getValue();
            String employeeName = employees.get(employeeIndex++);

            output.append(formatDateAndEmployee(monthNumber, dayNumber, dayOfWeek, employeeName));
            output.append(LINE_SEPARATOR);
        }
        return output;
    }

    private static String formatDateAndEmployee(int monthNumber, int dayNumber, DayOfWeek dayOfWeek,
                                                String employeeName) {
        return String.format("%d월 %d일 %s %s",
                monthNumber,
                dayNumber,
                dayOfWeek.getKoreanLabel(),
                employeeName);
    }

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
}