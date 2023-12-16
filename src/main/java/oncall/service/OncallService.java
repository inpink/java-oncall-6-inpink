package oncall.service;

import static oncall.constants.IntegerConstants.DAY_A_WEEK_SIZE;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import oncall.domain.Employee;
import oncall.domain.Employees;
import oncall.domain.Orders;
import oncall.domain.date.Dates;
import oncall.domain.date.DayOfWeek;
import oncall.domain.date.Month;
import oncall.domain.date.Oncall;
import oncall.domain.date.StartDate;
import oncall.domain.date.holiday.Holidays;

public class OncallService {
    private static boolean isLastOncallEmployee(List<Employee> oncallEmployees, Employee employeeCandidate) {
        if (oncallEmployees.isEmpty()) {
            return false;
        }
        return oncallEmployees.get(oncallEmployees.size() - 1).equals(employeeCandidate);
    }

    private static Map<Integer, DayOfWeek> generateDays(Month month, Orders orders) {
        Map<Integer, DayOfWeek> days = new LinkedHashMap<>();
        final int lastDayNumber = month.getLastDay();

        for (int dayNumber = 0; dayNumber < lastDayNumber; dayNumber++) {
            DayOfWeek day = orders.findDay(calculateDayNumber(dayNumber));
            days.put(dayNumber + 1, day);
        }
        return days;
    }

    private static int calculateDayNumber(int dayNumber) {
        return dayNumber % (DAY_A_WEEK_SIZE.getValue());
    }

    public Dates generateDates(StartDate startDate) {
        Month month = startDate.getMonth();
        DayOfWeek startDay = startDate.getDay();
        Orders orders = Orders.create(startDay);

        Map<Integer, DayOfWeek> days = generateDays(month, orders);

        return Dates.create(month, days);
    }

    public Oncall generateOncall(StartDate startDate, Dates dates, Employees workdayEmployees, Employees holidayEmployees) {
        Month monthNumber = startDate.getMonth();
        Map<Integer, DayOfWeek> days = dates.getDays();
        List<Employee> oncallEmployees = new ArrayList<>();

        int[] workdayEmployeeIndex = new int[]{0};
        int[] holidayEmployeeIndex = new int[]{0};

        for (int dayNumber : days.keySet()) {
            DayOfWeek day = days.get(dayNumber);
            assignWorkdayEmployee(Holidays.isHoliday(monthNumber, dayNumber, day),
                    oncallEmployees, holidayEmployees, holidayEmployeeIndex);
            assignHolidayEmployee(workdayEmployees, dayNumber, monthNumber,
                    day, oncallEmployees, workdayEmployeeIndex);
        }
        return Oncall.create(oncallEmployees);
    }

    private void assignWorkdayEmployee(boolean monthNumber, List<Employee> oncallEmployees, Employees holidayEmployees,
                                       int[] holidayEmployeeIndex) {
        if (monthNumber) {
            assignEmployee(oncallEmployees, holidayEmployees, holidayEmployeeIndex);
        }
    }

    private void assignHolidayEmployee(Employees workdayEmployees, int dayNumber, Month monthNumber, DayOfWeek day,
                                       List<Employee> oncallEmployees, int[] workdayEmployeeIndex) {
        assignWorkdayEmployee(!Holidays.isHoliday(monthNumber, dayNumber, day), oncallEmployees, workdayEmployees,
                workdayEmployeeIndex);
    }

    private void assignEmployee(List<Employee> oncallEmployees, Employees employees, int[] employeeIndex) {
        Employee candidate = employees.findEmployee(calculateEmployeeIndex(employees, employeeIndex));
        if (isLastOncallEmployee(oncallEmployees, candidate)) {
            candidate = employees.findEmployee(employeeIndex[0]);
            employeeIndex[0]++;
        }

        oncallEmployees.add(candidate);
        employeeIndex[0]++;
    }

    private static int calculateEmployeeIndex(Employees employees, int[] employeeIndex) {
        return employeeIndex[0] % employees.getSize();
    }

}
