package oncall.domain;

import java.util.List;

public class Employees {
    private final List<Employee> employees;

    private Employees(List<String> employeesNames) {
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
}
