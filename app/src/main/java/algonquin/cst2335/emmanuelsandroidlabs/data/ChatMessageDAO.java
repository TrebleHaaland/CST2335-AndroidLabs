package algonquin.cst2335.emmanuelsandroidlabs.data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import algonquin.cst2335.emmanuelsandroidlabs.ChatMessage;
@Dao
public interface ChatMessageDAO {
    @Insert
    public long insertMessage (ChatMessage m);
    @Query("Select * from ChatMessage")
    public List <ChatMessage> getAllMessages();
    @Delete
    void deleteMessage(ChatMessage m);

}