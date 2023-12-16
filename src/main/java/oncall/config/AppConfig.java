package oncall.config;

import oncall.controller.OncallController;
import oncall.service.OncallService;
import oncall.view.InputView;
import oncall.view.OutputView;

public class AppConfig {

    public static AppConfig getInstance() {
        return LazyHolder.INSTANCE;
    }

    public OncallController oncallController() {
        return LazyHolder.oncallController;
    }

    public OncallService oncallService() {
        return LazyHolder.oncallService;
    }

    public InputView inputView() {
        return LazyHolder.inputView;
    }

    public OutputView outputView() {
        return LazyHolder.outputView;
    }

    private static class LazyHolder {
        private static final AppConfig INSTANCE = new AppConfig();
        private static final InputView inputView = createInputView();
        private static final OutputView outputView = createOutputView();
        private static final OncallService oncallService = createOncallService();
        private static final OncallController oncallController = createOncallController();

        private static OncallController createOncallController() {
            return new OncallController(
                    inputView,
                    outputView,
                    oncallService);
        }

        private static OncallService createOncallService() {
            return new OncallService();
        }

        private static InputView createInputView() {
            return new InputView();
        }

        private static OutputView createOutputView() {
            return new OutputView();
        }
    }
}