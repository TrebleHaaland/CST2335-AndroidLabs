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
    @ColumnInfo (name = "isSentButton")
    protected final boolean isSentButton;
    @ColumnInfo (name = "isReceivedButton")
    protected boolean isReceivedButton;
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;
    public ChatMessage(String message, String timeSent, boolean isSentButton, boolean isReceivedButton) {
        this.message = message;
        this.timeSent = timeSent;
        this.isSentButton = isSentButton;
        this.isReceivedButton = isReceivedButton;
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
    public  boolean isReceivedButton() {
        return isReceivedButton;
    }
}
