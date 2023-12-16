package oncall.domain.dto;

import java.util.List;
import java.util.Map;
import oncall.domain.date.DayOfWeek;

public record OncallDto(int monthNumber, Map<Integer, DayOfWeek> days, List<String> employees) {
}
