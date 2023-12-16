package oncall.messages;

public enum ErrorMessages {

    INVALID_INPUT("유효하지 않은 입력 값입니다. 다시 입력해 주세요."),
    INVALID_DATE("유효하지 않은 날짜입니다.");

    private static final String prefix = "[ERROR] ";
    private final String message;

    ErrorMessages(final String message) {
        this.message = prefix + message;
    }

    public String getMessage() {
        return message;
    }
}
