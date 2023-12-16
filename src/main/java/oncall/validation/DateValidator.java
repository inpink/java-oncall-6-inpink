package oncall.validation;

import static oncall.messages.ErrorMessages.INVALID_DATE;

import java.time.DateTimeException;
import java.time.LocalDate;
import oncall.util.ExceptionUtil;

public final class DateValidator {

    public static void validateExistInCalendar(final int year,
                                               final int month,
                                               final int date) {
        try {
            LocalDate.of(year, month, date);
        } catch (DateTimeException e) {
            ExceptionUtil.throwInvalidValueException(INVALID_DATE.getMessage());
        }
    }
}
