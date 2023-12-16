package oncall.view;

import java.util.List;
import java.util.Map;
import oncall.domain.date.DayOfWeek;
import oncall.domain.dto.OncallDto;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void outputOncallResult(OncallDto oncallDto) {
        int monthNumber = oncallDto.monthNumber();
        Map<Integer, DayOfWeek> days = oncallDto.days();
        List<String> employees = oncallDto.employees();

        StringBuilder output = new StringBuilder();
        int employeeIndex = 0;

        for (Map.Entry<Integer, DayOfWeek> entry : days.entrySet()) {
            int dayNumber = entry.getKey();
            DayOfWeek dayOfWeek = entry.getValue();
            String employeeName = employees.get(employeeIndex++);

            output.append(String.format("%d월 %d일 %s %s", monthNumber, dayNumber, dayOfWeek.getKoreanLabel(), employeeName));
            output.append(LINE_SEPARATOR);
        }

        System.out.print(output.toString());
    }

    public void outputErrorMessage(String message) {
        System.out.println(message);
    }
}