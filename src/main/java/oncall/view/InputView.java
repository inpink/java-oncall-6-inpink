package oncall.view;

import static oncall.messages.IOMessages.INPUT_ONCALL_MONTH_AND_DAY;

import oncall.domain.StartDate;
import oncall.mapper.StartDateMapper;
import oncall.util.InputUtil;

public class InputView {
    public StartDate inputStartDate() {
        System.out.println(INPUT_ONCALL_MONTH_AND_DAY.getMessage());
        String input = InputUtil.input();

        return StartDateMapper.toStartDate(input);
    }

}
