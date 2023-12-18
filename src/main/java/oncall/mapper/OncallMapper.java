package oncall.mapper;

import java.util.List;
import java.util.Map;
import oncall.domain.date.Dates;
import oncall.domain.date.DayOfWeek;
import oncall.domain.date.Oncall;
import oncall.domain.date.StartDate;
import oncall.domain.dto.OncallDto;

public class OncallMapper {

    private OncallMapper() {

    }

    public static OncallDto from(StartDate startDate, Dates dates, Oncall oncall) {
        int monthNumber = startDate.getMonth().getMonthNumber();
        Map<Integer, DayOfWeek> days = dates.getDays();
        List<String> employees = toEmployees(oncall);

        return new OncallDto(monthNumber, days, employees);
    }

    private static List<String> toEmployees(Oncall oncall) {
        return oncall.getOncallEmployees().stream()
                .map(employee -> employee.getNickName())
                .toList();
    }
}
