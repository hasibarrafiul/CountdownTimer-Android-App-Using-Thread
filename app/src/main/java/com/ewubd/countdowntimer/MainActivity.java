package com.ewubd.countdowntimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int mInterval = 100;
    private int id = 2019160036;
    private Handler mHandler;
    private TextView Timer;
    private Button Start, Reset, Pause;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHandler = new Handler();
        Start = findViewById(R.id.start);
        Reset = findViewById(R.id.reset);
        Pause = findViewById(R.id.pause);
        Start.setOnClickListener(v->startRepeatingTask());
        Reset.setOnClickListener(v->reset());
        Pause.setOnClickListener(v->stopRepeatingTask());
        findViewById(R.id.Exit).setOnClickListener(v->finish());
        Reset.setEnabled(false);
        Pause.setEnabled(false);

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        stopRepeatingTask();
    }
    Runnable mStatusChecker = new Runnable() {
        @Override
        public void run() {
            try {
                updateStatus();
            } finally {
                mHandler.postDelayed(mStatusChecker, mInterval);
            }
        }
    };
    void startRepeatingTask() {
        mStatusChecker.run();
        Start.setEnabled(false);
        Reset.setEnabled(true);
        Pause.setEnabled(true);
    }
    void stopRepeatingTask() {
        mHandler.removeCallbacks(mStatusChecker);
        Start.setEnabled(true);
        Pause.setEnabled(false);
    }
    void updateStatus(){
        if(id>0) id = id-1;
        Timer = findViewById(R.id.timerText);
        Timer.setText(String.valueOf(id));
    }
    void reset(){
        id = 2019160036;
        stopRepeatingTask();
        Timer = findViewById(R.id.timerText);
        Timer.setText(String.valueOf(id));
        Start.setEnabled(true);
        Reset.setEnabled(false);
    }
}