package oncall.mapper;

import java.util.Arrays;
import java.util.List;
import oncall.domain.StartDate;
import oncall.util.StringUtil;

public class StartDateMapper {
    public static StartDate toStartDate(String input) {
        String deleteSpaces = StringUtil.removeAllSpaces(input);
        List<String> separated = Arrays.stream(deleteSpaces.split(","))
                .toList();

        final String month = separated.get(0);
        final String day = separated.get(1);


        final StartDate

    }
}
