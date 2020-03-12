package com.example.handlertimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    TextView tvTimer;
    long startTime;
    Handler customHandler = new Handler();

    long counterStart=0;
    private static final String TAG = "MainActivity";



    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            tvTimer = (TextView) findViewById(R.id.tvTimer);
        }

        public static String getDateFromMillis(long d) {
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
            df.setTimeZone(TimeZone.getTimeZone("GMT"));
            String string= df.format(d*1000);
            return string;
        }

        public void start(View v) {
        startTime = SystemClock.uptimeMillis();
        customHandler.postDelayed(updateTimerThread, 0);
        }

        public void pause(View v) {
            customHandler.removeCallbacks(updateTimerThread);
        }

        public  void  skip(View v) {
            Log.d(TAG, "skip:");
            counterStart-=5;
        }

        private Runnable updateTimerThread = new Runnable() {
            public void run() {

               customHandler.postDelayed(this,1000);
               counterStart++;
               tvTimer.setText(getDateFromMillis(counterStart));


            }
        };
    }

