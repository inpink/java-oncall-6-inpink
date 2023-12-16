package oncall.domain.date;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Map;
import java.util.stream.Stream;
import oncall.domain.Orders;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class OrdersTest {

    private static Stream<Arguments> provideStartDaysAndExpectedOrders() {
        return Stream.of(
                Arguments.of(DayOfWeek.MONDAY, Map.of(
                        1, DayOfWeek.MONDAY,
                        2, DayOfWeek.TUESDAY,
                        3, DayOfWeek.WEDNESDAY,
                        4, DayOfWeek.THURSDAY,
                        5, DayOfWeek.FRIDAY,
                        6, DayOfWeek.SATURDAY,
                        7, DayOfWeek.SUNDAY)),
                Arguments.of(DayOfWeek.FRIDAY, Map.of(
                        1, DayOfWeek.FRIDAY,
                        2, DayOfWeek.SATURDAY,
                        3, DayOfWeek.SUNDAY,
                        4, DayOfWeek.MONDAY,
                        5, DayOfWeek.TUESDAY,
                        6, DayOfWeek.WEDNESDAY,
                        7, DayOfWeek.THURSDAY))
        );
    }

    @ParameterizedTest
    @MethodSource("provideStartDaysAndExpectedOrders")
    void 주어진_시작_요일에_따른_요일_순서_테스트(final DayOfWeek startDay,
                                 final Map<Integer, DayOfWeek> expectedOrders) {
        // When
        final Orders orders = Orders.create(startDay);
        final Map<Integer, DayOfWeek> orderMap = orders.getOrders();

        // Then
        for (Map.Entry<Integer, DayOfWeek> entry : expectedOrders.entrySet()) {
            assertEquals(entry.getValue(), orderMap.get(entry.getKey() - 1));
        }
    }
}
