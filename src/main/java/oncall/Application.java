package oncall;

import oncall.config.AppConfig;
import oncall.controller.OncallController;

public class Application {

    public static void main(String[] args) {
        AppConfig config = generateConfig();
        OncallController oncallController = generateOncallController(config);
        oncallController.play();
    }

    private static AppConfig generateConfig() {
        return AppConfig.getInstance();
    }

    private static OncallController generateOncallController(AppConfig config) {
        return config.oncallController();
    }

}
