package oncall.domain.date;

import java.util.List;
import oncall.domain.Employee;

public class Oncall {

    private final List<Employee> oncallEmployees;

    private Oncall(List<Employee> oncallEmployees) {
        this.oncallEmployees = oncallEmployees;
    }

    public static Oncall create(List<Employee> oncallEmployees) {
        return new Oncall(oncallEmployees);
    }
}
