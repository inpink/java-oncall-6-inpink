package oncall.domain.date;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;
import java.util.stream.Stream;

class OrdersTest {

    @ParameterizedTest
    @MethodSource("provideStartDaysAndExpectedOrders")
    void 주어진_시작_요일에_따른_요일_순서_테스트(final DayOfWeek startDay, final Map<DayOfWeek, Integer> expectedOrders) {
        // When
        final Orders orders = Orders.create(startDay);
        final Map<DayOfWeek, Integer> orderMap = orders.getOrders();

        // Then
        for (Map.Entry<DayOfWeek, Integer> entry : expectedOrders.entrySet()) {
            assertEquals(entry.getValue(), orderMap.get(entry.getKey()));
        }
    }

    private static Stream<Arguments> provideStartDaysAndExpectedOrders() {
        return Stream.of(
                Arguments.of(DayOfWeek.MONDAY, Map.of(
                        DayOfWeek.MONDAY, 1,
                        DayOfWeek.TUESDAY, 2,
                        DayOfWeek.WEDNESDAY, 3,
                        DayOfWeek.THURSDAY, 4,
                        DayOfWeek.FRIDAY, 5,
                        DayOfWeek.SATURDAY, 6,
                        DayOfWeek.SUNDAY, 7)),
                Arguments.of(DayOfWeek.FRIDAY, Map.of(
                        DayOfWeek.FRIDAY, 1,
                        DayOfWeek.SATURDAY, 2,
                        DayOfWeek.SUNDAY, 3,
                        DayOfWeek.MONDAY, 4,
                        DayOfWeek.TUESDAY, 5,
                        DayOfWeek.WEDNESDAY, 6,
                        DayOfWeek.THURSDAY, 7))
        );
    }
}
