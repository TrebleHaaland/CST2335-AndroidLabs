package ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import algonquin.cst2335.emmanuelsandroidlabs.R;

/**
 * This class represents the main activity of the application.
 * It allows users to check the complexity of a password.
 *
 * @author Emmanuel Alabi
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private EditText editText;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.myEditText);
        button = findViewById(R.id.myButton);

        button.setOnClickListener(clk -> {
            String password = editText.getText().toString();
            boolean isComplex = checkPasswordComplexity(password);

            if (isComplex) {
                textView.setText("Your password meets the requirements");
            } else {
                textView.setText("You shall not pass!");
            }
        });
    }

    /**
     * Checks if the input password meets complexity requirements.
     *
     * @param pw The string object that we are checking
     * @return Returns true if the password meets complexity requirements, false otherwise
     */
    private boolean checkPasswordComplexity(String pw) {
        boolean foundUpperCase, foundLowerCase, foundNumber, foundSpecial;
        foundUpperCase = foundLowerCase = foundNumber = foundSpecial = false;

        for (char c : pw.toCharArray()) {
            if (Character.isUpperCase(c)) {
                foundUpperCase = true;
            } else if (Character.isLowerCase(c)) {
                foundLowerCase = true;
            } else if (Character.isDigit(c)) {
                foundNumber = true;
            } else if (isSpecialCharacter(c)) {
                foundSpecial = true;
            }
        }

        if (!foundUpperCase) {
            Toast.makeText(this, "Password is missing an upper case letter", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!foundLowerCase) {
            Toast.makeText(this, "Password is missing a lower case letter", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!foundNumber) {
            // Handle missing number case
            return false;
        } else if (!foundSpecial) {
            // Handle missing special character case
            return false;
        } else {
            return true;
        }
    }

    /**
     * Checks if a character is a special character.
     *
     * @param c The character to check
     * @return Returns true if c is one of: #$%^&*!@?, false otherwise
     */
    private boolean isSpecialCharacter(char c) {
        switch (c) {
            case '#':
            case '$':
            case '%':
            case '^':
            case '&':
            case '*':
            case '!':
            case '@':
            case '?':
                return true;
            default:
                return false;
        }
    }
}
