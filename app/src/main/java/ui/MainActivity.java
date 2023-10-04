package ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import algonquin.cst2335.emmanuelsandroidlabs.R;
import algonquin.cst2335.emmanuelsandroidlabs.data.MainViewModel;
import algonquin.cst2335.emmanuelsandroidlabs.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainViewModel model;
    private ActivityMainBinding variableBinding;
    private MutableLiveData<Boolean> myBooleanVariable;
    private EditText editText;
    private Button myButton;
    private TextView displayTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(MainViewModel.class);
        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        myBooleanVariable = new MutableLiveData<>();
        editText = variableBinding.myEditText;
        myButton = variableBinding.myButton;
        displayTextView = findViewById(R.id.textView); // Initialize the TextView

        variableBinding.textView.setText(model.getEditString());

        // Clicking the radio button triggers checking the checkbox and switch
        RadioButton radioButton = findViewById(R.id.radioButton);
        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                CheckBox checkBox = findViewById(R.id.checkBox);
                Switch switchButton = findViewById(R.id.switch1);
                checkBox.setChecked(isChecked); // Check/uncheck the checkbox
                switchButton.setChecked(isChecked); // Check/uncheck the switch
            }
        });

        variableBinding.myButton.setOnClickListener(click -> {
            // Show what was written in the EditText
            String text = editText.getText().toString();
            displayTextView.setText("Text entered: " + text); // Update the TextView with entered text
        });

        myBooleanVariable.observe(this, isChecked -> {
            Toast.makeText(MainActivity.this, "The value is now: " + isChecked, Toast.LENGTH_SHORT).show();
        });

        ImageButton my_ImageButton = findViewById(R.id.action_image);
        variableBinding.actionImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int width = variableBinding.actionImage.getWidth();
                int height = variableBinding.actionImage.getHeight();

                Toast.makeText(MainActivity.this, "The width = " + width + " and height = " + height, Toast.LENGTH_SHORT).show();
            }
        });

        myBooleanVariable.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isChecked) {
                // Checkbox and switch behavior is handled by the radio button listener
            }
        });
    }
}
