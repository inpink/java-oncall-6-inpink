package oncall.mapper;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import oncall.domain.Employees;
import oncall.util.ExceptionUtil;
import oncall.util.StringUtil;

public class EmployeesMapper {

    private EmployeesMapper() {

    }

    public static Employees toEmployees(String input) {
        List<String> separated = seperate(input);
        validateDuplicated(separated);

        return Employees.create(separated);
    }

    private static List<String> seperate(String input) {
        String deleteSpaces = StringUtil.removeAllSpaces(input);
        List<String> separated = Arrays.stream(deleteSpaces.split(","))
                .toList();
        return separated;
    }

    private static void validateDuplicated(List<String> separated) {
        int size = separated.size();
        int deduplicatedSize = new HashSet<>(separated).size();

        if (size != deduplicatedSize) {
            ExceptionUtil.throwInvalidValueException();
        }
    }
}
