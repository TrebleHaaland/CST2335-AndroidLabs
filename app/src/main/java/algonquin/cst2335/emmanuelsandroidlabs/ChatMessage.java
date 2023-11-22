package algonquin.cst2335.emmanuelsandroidlabs;

public class ChatMessage {

    private String message;
    private String timeSent;
    private boolean isSentButton;
    private  boolean isReceivedButton;

    public ChatMessage(String m, String t, boolean sent, boolean receive) {
        message = m;
        timeSent = t;
        isSentButton = sent;
        isReceivedButton = receive;
    }

    public String getMessage() {
        return message;
    }

    public String getTimeSent() {
        return timeSent;
    }

    public boolean isSentButton() {
        return isSentButton;
    }
    public  boolean isReceivedButton() {return isReceivedButton;}
}
