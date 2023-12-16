package oncall.domain.date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import oncall.domain.date.StartDate;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class StartDateTest {

    @ParameterizedTest
    @CsvSource({
            "1, 월",
            "2, 화",
            "12, 일"
    })
    void 정상_생성_테스트(final int month, final String day) {
        // When
        final StartDate startDate = StartDate.create(month, day);

        // Then
        assertNotNull(startDate);
        assertEquals(month, startDate.getMonth().getMonthNumber());
        assertEquals(day, startDate.getDay().getKoreanLabel());
    }

    @ParameterizedTest
    @CsvSource({
            "0, 화",
            "5, 묘", // 존재하지 않는 요일
            "-1, 금"
    })
    void 잘못된_값에_대한_예외_테스트(final int month, final String day) {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> StartDate.create(month, day));
    }
}