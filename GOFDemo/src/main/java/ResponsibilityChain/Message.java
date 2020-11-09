package ResponsibilityChain;

public class Message {
    private String message = "";

    public String add(String newMess) {
        return message = message + newMess;
    }
    public int getMesLength() {
        return message.length();
    }

    public String getMessage() {
        return message;
    }
}
