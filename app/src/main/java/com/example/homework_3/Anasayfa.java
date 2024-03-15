package com.example.homework_3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

public class Anasayfa  extends AppCompatActivity {
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    Handler handler;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle saveInstanceState) {

        super.onCreate(saveInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.anasayfa);

        sharedPreferences= getSharedPreferences("MyPref",MODE_PRIVATE);

        TextView countdown= (TextView) findViewById(R.id.countdown);
        TextView txtRegistration= (TextView) findViewById(R.id.txtRegistration);
        EditText date = (EditText) findViewById(R.id.txtDate);
        Button btnNext = (Button) findViewById(R.id.btnNext);
       progressBar = (ProgressBar) findViewById(R.id.progressBar);
        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                countdown.setText("Seconds left: " + millisUntilFinished / 1000);
                txtRegistration.setVisibility(View.INVISIBLE);
                date.setVisibility(View.INVISIBLE);
                btnNext.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
            }

            public void onFinish() {
                txtRegistration.setVisibility(View.VISIBLE);
                date.setVisibility(View.VISIBLE);
                btnNext.setVisibility(View.VISIBLE);
                countdown.setVisibility(View.VISIBLE);
            }
        }.start();
        handler= new Handler();
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor= sharedPreferences.edit();
                editor.putString("date",date.getText().toString());
                editor.apply();
                progressBar.setVisibility(View.VISIBLE);
                progressBar.setProgress(0);
                startProgressBar();
            }
        });


    }
    private void startProgressBar() {
        // Set the duration of the progress bar animation in milliseconds
        final int duration = 10000; // 10 seconds

        // Calculate the interval between each progress update
        final int interval = 100; // 100 milliseconds

        // Calculate the number of steps needed to reach 100%
        final int steps = duration / interval;

        // Calculate the progress increment for each step
        final float increment = 100.0f / steps;

        // Start the progress bar animation using a Handler and Runnable
        handler.postDelayed(new Runnable() {
            float progress = 0;

            @Override
            public void run() {
                progress += increment;
                progressBar.setProgress((int) progress);

                // Check if the progress has reached 100%
                if (progress < 100) {
                    // If not, schedule the next update after the interval
                    handler.postDelayed(this, interval);
                } else {
                    // If yes, hide the progress bar and navigate to the result page
                    progressBar.setVisibility(View.INVISIBLE);
                    Intent I4=new Intent(Anasayfa.this, Sonuc.class);
                    startActivity(I4);
                }
            }
        }, interval);
    }
}
