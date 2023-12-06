package algonquin.cst2335.emmanuelsandroidlabs.data;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import algonquin.cst2335.emmanuelsandroidlabs.ChatMessage;
import algonquin.cst2335.emmanuelsandroidlabs.databinding.DetailsLayoutBinding;

public class MessageDetailsFragment extends Fragment {
        ChatMessage selected;
        public MessageDetailsFragment(ChatMessage m){
            selected = m;
        }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        DetailsLayoutBinding layoutBinding = DetailsLayoutBinding.inflate(inflater);

        layoutBinding.messagesTextView.setText(selected.getMessage());
        layoutBinding.timeTextView.setText(selected.getTimeSent());
        layoutBinding.databaseTextView.setText("Id = " + selected.id);

        return layoutBinding.getRoot();
    }
}
