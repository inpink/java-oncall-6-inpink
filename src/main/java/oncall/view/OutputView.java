package oncall.view;

import oncall.domain.date.Oncall;

public class OutputView {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    public void outputOncallResult(Oncall oncall) {

    }
    public void outputErrorMessage(String message) {
        System.out.println(message);
    }
}