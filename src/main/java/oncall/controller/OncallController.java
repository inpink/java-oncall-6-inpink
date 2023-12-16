package oncall.controller;

import static oncall.messages.ErrorMessages.INVALID_INPUT;

import java.util.List;
import java.util.Map;
import oncall.domain.Employees;
import oncall.domain.date.Dates;
import oncall.domain.date.DayOfWeek;
import oncall.domain.date.Oncall;
import oncall.domain.date.StartDate;
import oncall.domain.dto.OncallDto;
import oncall.mapper.OncallMapper;
import oncall.service.OncallService;
import oncall.util.InputUtil;
import oncall.util.Pair;
import oncall.view.InputView;
import oncall.view.OutputView;

public class OncallController {
    private final InputView inputView;
    private final OutputView outputView;
    private final OncallService oncallService;

    public OncallController(final InputView inputView,
                            final OutputView outputView,
                            final OncallService oncallService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.oncallService = oncallService;
    }

    public void play() {
        StartDate startDate = inputValidStartDate();
        Dates dates = oncallService.generateDates(startDate);

        Pair<Employees, Employees> employeesPair = inputValidWorkdayAndHolidayEmployees();
        Employees workdayEmployees = employeesPair.getFirst();
        Employees holidayEmployees = employeesPair.getSecond();

        Oncall oncall = oncallService.generateOncall(
                startDate, dates,
                workdayEmployees,
                holidayEmployees);

        OncallDto oncallDto = OncallMapper.from(startDate, dates, oncall);
        outputResult(oncallDto);
    }

    private StartDate inputValidStartDate() {
        return InputUtil.retryOnInvalidInput(inputView::inputStartDate,
                errorMessage -> outputView.outputErrorMessage(INVALID_INPUT.getMessage()));
    }

    private Employees inputValidWorkdayEmployees() {
        return InputUtil.retryOnInvalidInput(inputView::inputWorkdayEmployees,
                errorMessage -> outputView.outputErrorMessage(INVALID_INPUT.getMessage()));
    }

    private Employees inputValidHolidayEmployees() {
        return InputUtil.retryOnInvalidInput(inputView::inputHolidayEmployees,
                errorMessage -> outputView.outputErrorMessage(INVALID_INPUT.getMessage()));
    }

    private Pair<Employees, Employees> inputValidWorkdayAndHolidayEmployees() {
        while (true) {
            try {
                Employees workdayEmployees = inputView.inputWorkdayEmployees();
                Employees holidayEmployees = inputView.inputHolidayEmployees();
                return new Pair<>(workdayEmployees, holidayEmployees);
            } catch (IllegalArgumentException e) {
                outputView.outputErrorMessage(INVALID_INPUT.getMessage());
            }
        }
    }

    private void outputResult(OncallDto oncallDto) {
        outputView.outputOncallResult(oncallDto);
    }
}
