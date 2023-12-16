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

    public Dates generateDates(StartDate startDate) {
        Month month = startDate.getMonth();
        DayOfWeek startDay = startDate.getDay();
        Orders orders = Orders.create(startDay);

        Map<Integer, DayOfWeek> days = generateDays(month, orders);

        return Dates.create(month, days);
    }

    public Oncall generateOncall(StartDate startDate, Dates dates,
                                 Employees workdayEmployees, Employees holidayEmployees) {
        Month monthNumber = startDate.getMonth();
        Map<Integer, DayOfWeek> days = dates.getDays();
        List<Employee> oncallEmployees = new ArrayList<>();

        int[] workdayEmployeeIndex = new int[]{0};
        int[] holidayEmployeeIndex = new int[]{0};

        boolean isWorkdayChanged = false;
        boolean isHolidayChanged = false;

        for (int dayNumber : days.keySet()) {
            DayOfWeek day = days.get(dayNumber);

            if (Holidays.isHoliday(monthNumber, dayNumber, day)) {
                isWorkdayChanged = assignEmployee(oncallEmployees, holidayEmployees,
                        holidayEmployeeIndex, isWorkdayChanged);
            }

            if (!Holidays.isHoliday(monthNumber, dayNumber, day)) {
                isHolidayChanged = assignEmployee(oncallEmployees, workdayEmployees,
                        workdayEmployeeIndex, isWorkdayChanged);
            }
        }
        return Oncall.create(oncallEmployees);
    }

    private boolean assignEmployee(List<Employee> oncallEmployees, Employees employees,
                                   int[] employeeIndex, boolean isChanged) {
        Employee candidate = employees.findEmployee(employeeIndex[0] % employees.getSize());
        if (isChanged) {
            candidate = employees.findEmployee((employeeIndex[0] - 1) % employees.getSize());
            isChanged = false;
        }

        if (isLastOncallEmployee(oncallEmployees, candidate)) {
            candidate = employees.findEmployee((employeeIndex[0] + 1) % employees.getSize());
            isChanged = true;
        }

        oncallEmployees.add(candidate);
        employeeIndex[0]++;
        return isChanged;
    }

    private static boolean isLastOncallEmployee(List<Employee> oncallEmployees, Employee employeeCandidate) {
        if (oncallEmployees.isEmpty()) {
            return false;
        }

        String lastEmployeeNickName = oncallEmployees.get(oncallEmployees.size() - 1).getNickName();
        return lastEmployeeNickName.equals(employeeCandidate.getNickName());
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

}