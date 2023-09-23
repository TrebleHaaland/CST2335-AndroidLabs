package ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import algonquin.cst2335.emmanuelsandroidlabs.R;
import algonquin.cst2335.emmanuelsandroidlabs.data.MainViewModel;
import algonquin.cst2335.emmanuelsandroidlabs.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainViewModel model;
    private ActivityMainBinding variableBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        model = new ViewModelProvider(this).get(MainViewModel.class);
        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());
        variableBinding.textView.setText(model.editString);
        variableBinding.myButton.setOnClickListener(click ->
        {
            model.editString = variableBinding.myEditText.getText().toString();
            variableBinding.myEditText.setText("Your edit text has: " + model.editString);
        });
    }
}

        /**
         * setContentView(R.layout.activity_main);
        Button my_Button = findViewById(R.id.myButton);
        TextView my_textView = variableBinding.textView;
        EditText my_edittext = findViewById(R.id.myEditText);
        String editString = my_edittext.getText().toString();
        my_textView.setText("Your edit has:" + editString);
        my_Button.setOnClickListener(vw -> {
            my_textView.setText("Your edit text has: " + editString);
        });
        if (my_Button != null) {
            my_Button.setOnClickListener(v -> {

            });
        }

    }
}
         **/