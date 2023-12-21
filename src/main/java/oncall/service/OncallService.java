package oncall.service;

import static oncall.constants.IntegerConstants.DAY_A_WEEK_SIZE;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

    public Oncall generateOncall(Dates dates, Employees workdayEmployees, Employees holidayEmployees) {
        List<Employee> oncallEmployees = new ArrayList<>();
        Deque<Employee> holidayEmployeesRotation = new ArrayDeque<>(holidayEmployees.getEmployees());
        Deque<Employee> workdayEmployeesRotation = new ArrayDeque<>(workdayEmployees.getEmployees());

        Optional<Employee> nextHolidayEmployee = Optional.empty();
        Optional<Employee> nextWorkdayEmployee = Optional.empty();

        for (int dayNumber : dates.getDayNumbers()) {
            if (Holidays.isHoliday(dates.getMonth(), dayNumber, dates.getDay(dayNumber))) { //휴일
                nextHolidayEmployee = addEmployee(nextHolidayEmployee, oncallEmployees, holidayEmployeesRotation);
                continue;
            }
            nextWorkdayEmployee = addEmployee(nextWorkdayEmployee, oncallEmployees, workdayEmployeesRotation);
        }
        return Oncall.create(oncallEmployees);
    }

    private static Optional<Employee> addEmployee(Optional<Employee> nextEmployee,
                                                  List<Employee> oncallEmployees,
                                                  Deque<Employee> employeesRotation) {
        if (nextEmployee.isPresent()) {  // 만약 이전에 스위칭이 발생했으면
            oncallEmployees.add(nextEmployee.get());
            return Optional.empty();
        }

        //스위칭 발생 X
        return addUnswitchedEmployee(nextEmployee, oncallEmployees, employeesRotation);
    }

    private static Optional<Employee> addUnswitchedEmployee(
            Optional<Employee> nextEmployee,
            List<Employee> oncallEmployees,
            Deque<Employee> employeesRotation) {
        Employee employee = employeesRotation.pollFirst();
        employeesRotation.addLast(employee);

        //만약 연속이면
        if (isContinuous(oncallEmployees, employee)) {
            return addSwitchedEmployee(oncallEmployees, employeesRotation, employee);
        }
        oncallEmployees.add(employee); //연속 아니면 // 지금 껏 그대로 씀
        return nextEmployee;
    }

    private static Optional<Employee> addSwitchedEmployee(List<Employee> oncallEmployees,
                                                          Deque<Employee> employeesRotation,
                                                          Employee employee) {
        Optional<Employee> nextEmployee;
        nextEmployee = Optional.ofNullable(employee); // 다음 사용은 이번 것
        Employee switchedEmployee = employeesRotation.pollFirst();

        // 넣는건 순서대로 넣어줌
        employeesRotation.addLast(switchedEmployee);
        oncallEmployees.add(switchedEmployee);
        return nextEmployee;
    }

    private static boolean isContinuous(List<Employee> oncallEmployees, Employee candidate) {
        if (oncallEmployees.size() == 0) {
            return false;
        }

        Employee beforeEmployee = oncallEmployees.get(oncallEmployees.size() - 1);
        if (candidate.equals(beforeEmployee)) { // 중복됐으면
            return true;
        }
        return false;
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