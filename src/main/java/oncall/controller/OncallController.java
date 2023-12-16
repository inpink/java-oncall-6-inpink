package oncall.controller;

import static oncall.messages.ErrorMessages.INVALID_INPUT;

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

    }

    private StartDate inputValidStartDate() {
        return InputUtil.retryOnInvalidInput(inputView::inputStartDate,
                errorMessage -> outputView.outputErrorMessage(INVALID_INPUT.getMessage()));
    }

    private void outputResult() {

    }
}
