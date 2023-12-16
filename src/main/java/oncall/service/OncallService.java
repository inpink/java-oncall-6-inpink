package oncall.service;

import static oncall.constants.IntegerConstants.DAY_ORDER_LAST_NUMBER;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import oncall.domain.Employee;
import oncall.domain.Employees;
import oncall.domain.date.Dates;
import oncall.domain.date.DayOfWeek;
import oncall.domain.date.Month;
import oncall.domain.Orders;
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


    public Oncall generateOncall(StartDate startDate,
                                 Dates dates,
                                 Employees workdayEmployees,
                                 Employees holidayEmployees) {
        Month monthNumber = startDate.getMonth();
        Map<Integer, DayOfWeek> days = dates.getDays();
        List<Employee> oncallEmployees = new ArrayList<>();

        int workdayEmployeeIndex = 0;
        int holidayEmployeeIndex = 0;
        for (int dayNumber : days.keySet()) {
            DayOfWeek day = days.get(dayNumber);

            boolean isWorkdaySequenceChanged = false;
            boolean isHolidaySequenceChanged = false;
            if (Holidays.isHoliday(monthNumber, dayNumber, day)) { // 휴일이라면
                System.out.println("휴일");
                Employee holidaymployeeCandidate
                        = holidayEmployees.findEmployee(holidayEmployeeIndex);

                if (isHolidaySequenceChanged) {
                    Employee beforeHolidaymployeeCandidate
                            = holidayEmployees.findEmployee(holidayEmployeeIndex - 1);
                    holidayEmployeeIndex++;
                    isHolidaySequenceChanged = false;
                } else {
                    // 이전날에 수아가 이미 일함
                    if (isLastOncallEmployee(oncallEmployees, holidaymployeeCandidate)) {
                        Employee nextHolidaymployeeCandidate
                                = holidayEmployees.findEmployee(holidayEmployeeIndex + 1);
                        oncallEmployees.add(nextHolidaymployeeCandidate);
                        holidayEmployeeIndex++;
                        isHolidaySequenceChanged = true;
                    } else { // 이전날에 수아가 이미 일 안함
                        oncallEmployees.add(holidaymployeeCandidate);
                        holidayEmployeeIndex++;
                        isHolidaySequenceChanged = false;
                    }
                }

            }

            if (!Holidays.isHoliday(monthNumber, dayNumber, day)) { // 평일
                Employee workdaymployeeCandidate
                        = workdayEmployees.findEmployee(workdayEmployeeIndex);

                if (isWorkdaySequenceChanged) {
                    Employee beforeWorkdaymployeeCandidate
                            = workdayEmployees.findEmployee(workdayEmployeeIndex - 1);
                    workdayEmployeeIndex++;
                    isWorkdaySequenceChanged = false;
                }
                else {
                    // 이전날에 수아가 이미 일함
                    if (isLastOncallEmployee(oncallEmployees, workdaymployeeCandidate)) {
                        Employee nextWorkdaymployeeCandidate
                                = workdayEmployees.findEmployee(workdayEmployeeIndex + 1);
                        oncallEmployees.add(nextWorkdaymployeeCandidate);
                        workdayEmployeeIndex++;
                        isWorkdaySequenceChanged = true;
                    } else { // 이전날에 수아가 이미 일 안함
                        oncallEmployees.add(workdaymployeeCandidate);
                        workdayEmployeeIndex++;
                        isWorkdaySequenceChanged = false;
                    }
                }

            }
        }

        oncallEmployees.stream().forEach(a -> System.out.print(a.getNickName()+ " "));

        return Oncall.create(oncallEmployees);
    }

    private static boolean isLastOncallEmployee(List<Employee> oncallEmployees, Employee employeeCandidate) {
        if (oncallEmployees.size() == 0) {
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
        return dayNumber % (DAY_ORDER_LAST_NUMBER.getValue());
    }

}
