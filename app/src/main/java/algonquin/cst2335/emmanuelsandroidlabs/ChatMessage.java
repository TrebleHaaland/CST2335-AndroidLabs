package algonquin.cst2335.emmanuelsandroidlabs;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ChatMessage {
    @ColumnInfo (name = "message")
    public String message;
    @ColumnInfo (name = "TimeSent")
    private String timeSent;
    @ColumnInfo (name = "SendButton")
    private final boolean isSentButton;
    @ColumnInfo (name = "ReceiveButton")
    private  boolean isReceivedButton;
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
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
