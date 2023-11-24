package algonquin.cst2335.emmanuelsandroidlabs;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

    @Entity
public class ChatMessage {
    @ColumnInfo (name = "message")
    protected String message;
    @ColumnInfo (name = "TimeSent")
    protected String timeSent;
    @ColumnInfo (name = "SendButton")
    protected final boolean isSentButton;
    @ColumnInfo (name = "ReceiveButton")
    protected  boolean isReceivedButton;
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
