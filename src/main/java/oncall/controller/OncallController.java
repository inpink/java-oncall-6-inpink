package oncall.controller;

import static oncall.messages.ErrorMessages.INVALID_INPUT;

import oncall.domain.Employees;
import oncall.domain.StartDate;
import oncall.service.OncallService;
import oncall.util.InputUtil;
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
        Employees workdayEmployees = inputValidWorkdayEmployees();
        Employees holidayEmployees = inputValidHolidayEmployees();
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
                // 예외 발생 시 메시지 출력 후 처음부터 재시작
            }
        }
    }


    private void outputResult() {

    }
}
