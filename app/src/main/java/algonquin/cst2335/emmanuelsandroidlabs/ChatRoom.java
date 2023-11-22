package algonquin.cst2335.emmanuelsandroidlabs;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
            String messageText = binding.editTextText.getText().toString();
            sendMessage(messageText, true);
        });

        binding.receiveButton.setOnClickListener(click -> {
            String messageText = binding.editTextText.getText().toString();
            receiveMessage(messageText, true);
        });

        myAdapter = new MyAdapter();
        binding.recycleView.setAdapter(myAdapter);
        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void sendMessage(String messageText, boolean isSentButton) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
        String currentDateAndTime = sdf.format(new Date());
        ChatMessage chatMessage = new ChatMessage(messageText, currentDateAndTime, isSentButton);
        messages.add(chatMessage);
        myAdapter.notifyItemInserted(messages.size() - 1);
        binding.editTextText.setText("");

    }
    private void receiveMessage(String messageText, boolean isReceivedButton) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
        String currentDateAndTime = sdf.format(new Date());
        ChatMessage chatMessage = new ChatMessage(messageText, currentDateAndTime, isReceivedButton);
        messages.add(chatMessage);
        myAdapter.notifyItemInserted(messages.size() - 1);
        binding.editTextText.setText("");


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

            // Set image based on isSentButton value
            if (chatMessage.isSentButton()) {
                holder.imageView.setImageResource(R.drawable.sent_message);
            } else {
                holder.imageView.setImageResource(R.drawable.receive_message);
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
