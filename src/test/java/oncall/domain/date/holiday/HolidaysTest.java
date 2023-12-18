package oncall.domain.date.holiday;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import oncall.domain.date.DayOfWeek;
import oncall.domain.date.Month;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class HolidaysTest {

    private static Stream<Arguments> 기본휴일제공() {
        return Stream.of(
                Arguments.of(DayOfWeek.SATURDAY, true),
                Arguments.of(DayOfWeek.SUNDAY, true),
                Arguments.of(DayOfWeek.MONDAY, false)
        );
    }

    private static Stream<Arguments> 법정휴일제공() {
        return Stream.of(
                Arguments.of(Month.JANUARY, 1, true), // 신정
                Arguments.of(Month.MAY, 5, true), // 어린이날
                Arguments.of(Month.JUNE, 6, true), // 현충일
                Arguments.of(Month.JUNE, 7, false) // 휴일 아님
        );
    }

    @ParameterizedTest
    @MethodSource("기본휴일제공")
    void 기본휴일테스트(final DayOfWeek date, final boolean expected) {
        // When
        final boolean result = Holidays.isBasicHoliday(date);

        // Then
        assertEquals(expected, result);
    }

    @ParameterizedTest
    @MethodSource("법정휴일제공")
    void 법정휴일테스트(final Month month, final int dayNumber, final boolean expected) {
        // When
        final boolean result = Holidays.isLegalHoliday(month, dayNumber);

        // Then
        assertEquals(expected, result);
    }
}
