package oncall.domain;

import static oncall.constants.IntegerConstants.MAX_NICKNAME_LENGTH;
import static oncall.constants.IntegerConstants.MIN_NICKNAME_LENGTH;

import java.util.Objects;
import oncall.validation.IntegerValidator;

public class Employee {
    private final String nickName;

    private Employee(String nickName) {
        validateLength(nickName);
        this.nickName = nickName;
    }

    public static Employee create(String nickName) {
        return new Employee(nickName);
    }

    private void validateLength(String nickName) {
        IntegerValidator.validateInRange(
                nickName.length(),
                MIN_NICKNAME_LENGTH.getValue(),
                MAX_NICKNAME_LENGTH.getValue());
    }

    public String getNickName() {
        return nickName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Employee employee = (Employee) obj;
        return Objects.equals(nickName, employee.nickName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nickName);
    }

}
