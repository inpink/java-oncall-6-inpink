package oncall.mapper;

import static oncall.constants.IntegerConstants.MONTH_AND_DAY_COUNT;

import java.util.Arrays;
import java.util.List;
import oncall.domain.StartDate;
import oncall.util.ExceptionUtil;
import oncall.util.StringUtil;
import oncall.validation.IntegerValidator;

public class StartDateMapper {
    public static StartDate toStartDate(String input) {
        String deleteSpaces = StringUtil.removeAllSpaces(input);
        List<String> separated = Arrays.stream(deleteSpaces.split(","))
                .toList();
        validateSize(separated);

        final int validMonth = convertToValidMonth(separated);
        final String validDay = convertToValidDay(separated);

        return StartDate.create(validMonth, validDay);
    }

    private static void validateSize(List<String> separated) {
        if (separated.size() != MONTH_AND_DAY_COUNT.getValue()) {
            ExceptionUtil.throwInvalidValueException();
        }
    }

    private static String convertToValidDay(List<String> separated) {
        return separated.get(1);
    }

    private static int convertToValidMonth(List<String> separated) {
        final String month = separated.get(0);
        IntegerValidator.validateInteger(month);
        final int validMonth = Integer.parseInt(month);

        return validMonth;
    }
}
