package com.example.adni_gumilang.clickspeed;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    RelativeLayout field;
    TextView score, time;
    Button play;

    private CountDownTimer countDownTimer;
    private long timemilliseconds =10000; // 10 seconds


    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = findViewById(R.id.play);
        field = findViewById(R.id.field);
        score = findViewById(R.id.score);
        time = findViewById(R.id.time);

        score.setText("0");
        time.setText("10 sec");

        field.setEnabled(false);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play();
            }
        });

        field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                score.setText(""+count);
            }
        });
    }

    public void play() {
        play.setVisibility(View.GONE);
        field.setEnabled(true);
        count = 0;
    }

    private void startTime() {
        countDownTimer = new CountDownTimer(timemilliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timemilliseconds = 1;
                update();
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("score", count);
                startActivity(intent);
                finish();
            }
        }.start();
    }

    private void update() {
        int seconds = (int) timemilliseconds / 1000;
        time.setText(""+seconds);
    }
}
