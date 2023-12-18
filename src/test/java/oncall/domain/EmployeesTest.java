package oncall.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class EmployeesTest {

    @ParameterizedTest
    @MethodSource("유효한직원목록제공")
    void 직원_수_테스트_성공(final List<String> employeesNames) {
        // When
        final Employees employees = Employees.create(employeesNames);

        // Then
        assertEquals(employeesNames.size(), employees.getSize());
    }

    private static List<List<String>> 유효한직원목록제공() {
        return List.of(
                IntStream.rangeClosed(1, 5).mapToObj(i -> "직원" + i).collect(Collectors.toList()),
                IntStream.rangeClosed(1, 35).mapToObj(i -> "직원" + i).collect(Collectors.toList())
        );
    }

    @ParameterizedTest
    @MethodSource("유효하지않은직원목록제공")
    void 직원_수_테스트_실패(final List<String> employeesNames) {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            Employees.create(employeesNames);
        });
    }

    private static List<List<String>> 유효하지않은직원목록제공() {
        return List.of(
                IntStream.rangeClosed(1, 4).mapToObj(i -> "직원" + i).collect(Collectors.toList()), // 4명, 너무 적음
                IntStream.rangeClosed(1, 36).mapToObj(i -> "직원" + i).collect(Collectors.toList()) // 36명, 너무 많음
        );
    }
}
