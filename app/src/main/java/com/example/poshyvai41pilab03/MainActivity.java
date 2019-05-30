package com.example.poshyvai41pilab03;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int seconds = 0;
    boolean isRunning, wasRunning = false;
    TextView timeStart;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("key", seconds);
        outState.putBoolean(" wasRunning ", wasRunning);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timeStart =  findViewById(R.id.init_time);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("key");
            isRunning = savedInstanceState.getBoolean("key");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }

        runTimer();
    }

    @Override
    protected void onPause(){
        super.onPause();
        wasRunning = isRunning;
        isRunning = false;
    }
    @Override
    protected void onResume(){
        super.onResume();
        isRunning = wasRunning;
    }


    public void onClickStartTimer(View view) {
        isRunning = true;
    }

    public void onClickPauseTimer(View view) {
        isRunning = false;
    }

    public void onClickResetTimer(View view) {
        isRunning = false;
        seconds = 0;
    }

    public void runTimer() {
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int sec = seconds % 60;
                @SuppressLint("DefaultLocale") String time = String.format("%d:%02d:%02d", hours, minutes, sec);
                timeStart.setText(time);
                if(isRunning) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}
