package oncall.controller;

import static oncall.messages.ErrorMessages.INVALID_INPUT;

import oncall.domain.Employees;
import oncall.domain.date.Dates;
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
        final StartDate startDate = inputValidStartDate();
        final Dates dates = oncallService.generateDates(startDate);

        final Pair<Employees, Employees> employeesPair = inputValidWorkdayAndHolidayEmployees();
        final Employees workdayEmployees = employeesPair.getFirst();
        final Employees holidayEmployees = employeesPair.getSecond();

        final Oncall oncall = oncallService.generateOncall(
                startDate, dates,
                workdayEmployees,
                holidayEmployees);

        outputResult(startDate, dates, oncall);
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
                final Employees workdayEmployees = inputView.inputWorkdayEmployees();
                final Employees holidayEmployees = inputView.inputHolidayEmployees();
                return new Pair<>(workdayEmployees, holidayEmployees);
            } catch (IllegalArgumentException e) {
                outputView.outputErrorMessage(INVALID_INPUT.getMessage());
            }
        }
    }

    private void outputResult(final StartDate startDate,
                              final Dates dates,
                              final Oncall oncall) {
        final OncallDto oncallDto = OncallMapper.from(startDate, dates, oncall);
        outputView.outputOncallResult(oncallDto);
    }
}
