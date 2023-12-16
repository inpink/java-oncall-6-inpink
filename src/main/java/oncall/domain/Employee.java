package oncall.domain;

public class Employee {
    private final String nickName;

    private Employee(String nickName) {
        this.nickName = nickName;
    }

    public static Employee create(String nickName) {
        return new Employee(nickName);
    }

    public String getNickName() {
        return nickName;
    }
}
