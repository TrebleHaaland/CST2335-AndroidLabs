package ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import algonquin.cst2335.emmanuelsandroidlabs.R;
import algonquin.cst2335.emmanuelsandroidlabs.data.MainViewModel;
import algonquin.cst2335.emmanuelsandroidlabs.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private MainViewModel model;
    private ActivityMainBinding variableBinding;
    private MutableLiveData<Boolean> myBooleanVariable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new ViewModelProvider(this).get(MainViewModel.class);
        variableBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(variableBinding.getRoot());

        // Initialize myBooleanVariable
        myBooleanVariable = new MutableLiveData<>();

        variableBinding.textView.setText(model.getEditString());
        variableBinding.myButton.setOnClickListener(click -> {
            model.setEditString(variableBinding.myEditText.getText().toString());
            variableBinding.myEditText.setText("Your edit text has: " + model.getEditString());
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
                CheckBox checkBox = findViewById(R.id.checkBox);
                Switch switchButton = findViewById(R.id.switch1);
                RadioButton radioButton = findViewById(R.id.radioButton);

                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        myBooleanVariable.postValue(isChecked);
                    }
                });
                switchButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        myBooleanVariable.postValue(isChecked);
                    }
                });
                radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        myBooleanVariable.postValue(isChecked);
                    }
                });
            }
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