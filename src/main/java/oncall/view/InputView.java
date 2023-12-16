package oncall.view;

import static oncall.messages.IOMessages.HOLIDAY;
import static oncall.messages.IOMessages.INPUT_ONCALL_MONTH_AND_DAY;
import static oncall.messages.IOMessages.INPUT_ONCALL_NICKNAMES;
import static oncall.messages.IOMessages.WORKDAY;

import oncall.domain.Employees;
import oncall.domain.date.StartDate;
import oncall.mapper.EmployeesMapper;
import oncall.mapper.StartDateMapper;
import oncall.util.InputUtil;

public class InputView {
    public StartDate inputStartDate() {
        System.out.print(INPUT_ONCALL_MONTH_AND_DAY.getMessage());
        String input = InputUtil.input();

        return StartDateMapper.toStartDate(input);
    }

    public Employees inputWorkdayEmployees() {
        System.out.print(WORKDAY.getMessage() + " " + INPUT_ONCALL_NICKNAMES.getMessage());
        String input = InputUtil.input();

        return EmployeesMapper.toEmployees(input);
    }

    public Employees inputHolidayEmployees() {
        System.out.print(HOLIDAY.getMessage() + " " + INPUT_ONCALL_NICKNAMES.getMessage());
        String input = InputUtil.input();

        return EmployeesMapper.toEmployees(input);
    }

}
