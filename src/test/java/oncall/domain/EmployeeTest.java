package oncall.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EmployeeTest {

    @ParameterizedTest
    @ValueSource(strings = {"준", "도밥", "고니", "수아", "루루"}) // 유효한 닉네임
    void 닉네임_길이_테스트_성공(final String nickName) {
        // When
        final Employee employee = Employee.create(nickName);

        // Then
        assertEquals(nickName, employee.getNickName());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "너무긴닉네임"}) // 유효하지 않은 닉네임
    void 닉네임_길이_테스트_실패(final String nickName) {
        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            Employee.create(nickName);
        });
    }
}
