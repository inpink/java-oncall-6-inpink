package oncall.mapper;

import java.util.Arrays;
import java.util.List;
import oncall.domain.Employees;
import oncall.util.StringUtil;

public class EmployeesMapper {

    public static Employees toEmployees(String input) {
        List<String> separated = seperate(input);

        return Employees.create(separated);
    }

    private static List<String> seperate(String input) {
        String deleteSpaces = StringUtil.removeAllSpaces(input);
        List<String> separated = Arrays.stream(deleteSpaces.split(","))
                .toList();
        return separated;
    }
}
