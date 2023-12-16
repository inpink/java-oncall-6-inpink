package oncall.domain;

import static oncall.constants.IntegerConstants.MAX_EMPLOYEES_COUNT;
import static oncall.constants.IntegerConstants.MIN_EMPLOYEES_COUNT;

import java.util.List;
import oncall.validation.IntegerValidator;

public class Employees {
    private final List<Employee> employees;

    private Employees(List<String> employeesNames) {
        validateSize(employeesNames);
        this.employees = toEmployees(employeesNames);
    }

    public static Employees create(List<String> employeesNames) {
        return new Employees(employeesNames);
    }

    private List<Employee> toEmployees(List<String> employeesNames) {
        return employeesNames.stream()
                .map(name -> Employee.create(name))
                .toList();
    }

    public Employee findEmployee(int index) {
        return employees.get(index);
    }

    private void validateSize(List<String> employeesNames) {
        IntegerValidator.validateInRange(
                employeesNames.size(),
                MIN_EMPLOYEES_COUNT.getValue(),
                MAX_EMPLOYEES_COUNT.getValue()
        );
    }
}
