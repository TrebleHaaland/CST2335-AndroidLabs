package algonquin.cst2335.emmanuelsandroidlabs;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import algonquin.cst2335.emmanuelsandroidlabs.data.ChatMessageDAO;
import algonquin.cst2335.emmanuelsandroidlabs.data.MessageDatabase;
import algonquin.cst2335.emmanuelsandroidlabs.databinding.ActivityChatRoomBinding;
import algonquin.cst2335.emmanuelsandroidlabs.databinding.ReceiveMessageBinding;
import algonquin.cst2335.emmanuelsandroidlabs.databinding.SendMessageBinding;

public class ChatRoom extends AppCompatActivity {
    ActivityChatRoomBinding binding;
    ArrayList<ChatMessage> messages = new ArrayList<>();
    private MyAdapter myAdapter;

    class MyRowHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText;
        ImageView imageView;
        public MyRowHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(clk->{
                int position = getAbsoluteAdapterPosition();
                AlertDialog.Builder builder = new AlertDialog.Builder( ChatRoom.this );
                builder.setMessage("Do you want to delete the message: "+ messageText.getText())
                .setTitle("Question:")
                .setNegativeButton("No", (dialog, cl) -> {})
                .setPositiveButton("Yes", (dialog, cl)-> {
                    ChatMessage removedMessage = messages.get(position);
                    messages.remove(position);
                    myAdapter.notifyItemRemoved(position);
                    Snackbar.make(messageText, "You deleted message #"+ position,Snackbar.LENGTH_LONG)
                            .setAction("Undo", click -> {
                                messages.add(position, removedMessage);
                                myAdapter.notifyItemInserted(position);
                            })
                            .show();
                }).create().show();
            });
            messageText = itemView.findViewById(R.id.messageView);
            timeText = itemView.findViewById(R.id.timeView);
            imageView = itemView.findViewById(R.id.receive_image);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.sendButton.setOnClickListener(click -> {
            String messageText = binding.sendEditText.getText().toString();
            sendMessage(messageText, true);
        });

        binding.receiveButton.setOnClickListener(click -> {
            String messageText = binding.sendEditText.getText().toString();
            receiveMessage(messageText, true); // Use false for received messages
        });

        myAdapter = new MyAdapter();
        binding.recycleView.setAdapter(myAdapter);
        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));

        MessageDatabase db = Room.databaseBuilder(getApplicationContext(), MessageDatabase.class, "database-name").build();
        ChatMessageDAO mDAO = db.cmDAO();
        if(messages == null)
        {
            Executor thread = Executors.newSingleThreadExecutor();
            thread.execute(() ->
            {
                messages.addAll( mDAO.getAllMessages() ); //Once you get the data from database

                runOnUiThread( () ->  binding.recycleView.setAdapter( myAdapter )); //You can then load the RecyclerView
            });
        }

    }

    private void sendMessage(String messageText, boolean isSentButton) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
        String currentDateAndTime = sdf.format(new Date());
        ChatMessage chatMessage = new ChatMessage(messageText, currentDateAndTime, true,false);
        messages.add(chatMessage);
        myAdapter.notifyItemInserted(messages.size() - 1);
        binding.sendEditText.setText("");

        binding.recycleView.scrollToPosition(messages.size() - 1);
    }

    private void receiveMessage(String messageText, boolean isReceivedButton) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
        String currentDateAndTime = sdf.format(new Date());
        ChatMessage chatMessage = new ChatMessage(messageText, currentDateAndTime, false, true);
        messages.add(chatMessage);
        myAdapter.notifyItemInserted(messages.size() - 1);
        binding.sendEditText.setText("");

        binding.recycleView.scrollToPosition(messages.size() - 1);
    }


    private class MyAdapter extends RecyclerView.Adapter<MyRowHolder> {
        @NonNull
        @Override
        public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (viewType == 0) {
                SendMessageBinding sendBinding = SendMessageBinding.inflate(getLayoutInflater(), parent, false);
                return new MyRowHolder(sendBinding.getRoot());
            } else {
                ReceiveMessageBinding receiveBinding = ReceiveMessageBinding.inflate(getLayoutInflater(), parent, false);
                return new MyRowHolder(receiveBinding.getRoot());
            }
        }

        @Override
        public void onBindViewHolder(@NonNull MyRowHolder holder, int position) {
            ChatMessage chatMessage = messages.get(position);
            holder.messageText.setText(chatMessage.getMessage());
            holder.timeText.setText(chatMessage.getTimeSent());

            // Set image and gravity based on isSentButton value
            if (chatMessage.isSentButton()) {
                holder.imageView.setImageResource(R.drawable.sent_message);
                holder.messageText.setGravity(Gravity.END); // Align to the right
            } else {
                holder.imageView.setImageResource(R.drawable.receive_message);
                holder.messageText.setGravity(Gravity.START); // Align to the left
            }
        }
        @Override
        public int getItemCount() {
            return messages.size();
        }

        @Override
        public int getItemViewType(int position) {
            return messages.get(position).isSentButton() ? 0 : 1;
        }
    }
}
