package oncall.messages;

public enum IOMessages {

    WORKDAY("평일"),
    HOLIDAY("휴일"),
    INPUT_ONCALL_MONTH_AND_DAY("비상 근무를 배정할 월과 시작 요일을 입력하세요> "),
    INPUT_ONCALL_NICKNAMES("비상 근무 순번대로 사원 닉네임을 입력하세요> ");

    private final String message;

    IOMessages(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
