package ui;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onStart() {
        super.onStart();
        Log.w("Main Activity","The application is now visible on screen.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w("Main Activity","The application is no longer visible");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w("Main Activity","The application no longer responds to user input");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.w("Main Activity","The application is now responding to user input");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w("Main Activity","Any memory used by the application is freed.");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w("Main Activity","In onCreate() - Loading Widgets");



    }

}