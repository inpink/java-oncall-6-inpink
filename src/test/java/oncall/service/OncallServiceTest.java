package oncall.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import oncall.domain.date.*;
import java.util.Map;
import java.util.stream.Stream;

class OncallServiceTest {

    @ParameterizedTest
    @MethodSource("provideStartDatesAndExpectedDays")
    void 시작_요일에_따른_날짜_생성_테스트(final StartDate startDate, final Map<Integer, DayOfWeek> expectedDays) {
        // Given
        final OncallService oncallService = new OncallService();

        // When
        final Dates dates = oncallService.generateDates(startDate);

        // Then
        Map<Integer, DayOfWeek> actualDays = dates.getDays();
        for (Map.Entry<Integer, DayOfWeek> entry : expectedDays.entrySet()) {
            assertEquals(entry.getValue(), actualDays.get(entry.getKey()));
        }
    }

    private static Stream<Arguments> provideStartDatesAndExpectedDays() {
        return Stream.of(
                Arguments.of(
                        new StartDate(1, "금"),
                        Map.of(
                                1, DayOfWeek.FRIDAY,
                                2, DayOfWeek.SATURDAY,
                                3, DayOfWeek.SUNDAY,
                                4, DayOfWeek.MONDAY,
                                5, DayOfWeek.TUESDAY,
                                6, DayOfWeek.WEDNESDAY,
                                7, DayOfWeek.THURSDAY,
                                8, DayOfWeek.FRIDAY,
                                9, DayOfWeek.SATURDAY
                        )
                )
                // 추가 테스트 케이스
        );
    }
}
