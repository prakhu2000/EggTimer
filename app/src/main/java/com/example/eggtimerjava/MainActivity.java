package com.example.eggtimerjava;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
     TextView timerTextView;
    SeekBar timerSeekBar;
    boolean countIsActive=false;
    Button goButton;
    CountDownTimer countDownTimer;
    public void resetTimer(){
        timerSeekBar.setEnabled(true);
             timerTextView.setText("0:30");
             goButton.setText("GO!");
             timerSeekBar.setProgress(30);
             countIsActive=false;
             countDownTimer.cancel();
    }
     public void buttonClicked(View view) {
         if (countIsActive) {
resetTimer();
         }
         else {
             countIsActive = true;
             timerSeekBar.setEnabled(false);
             goButton.setText("STOP!");
             countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {

                 @Override
                 public void onTick(long l) {
                     updateTimer((int) l / 1000);
                 }

                 @Override
                 public void onFinish() {
                     //  Log.i("finished","timerkhtm");
                     MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhirn);
                     mPlayer.start();
                      resetTimer();
                 }
             }.start();
         }
     }
public void updateTimer(int secondsLeft){
    int min=secondsLeft/60;
    int sec=secondsLeft-(min*60);
    String secondStr=Integer.toString(sec);
    if(sec<=9)
        secondStr="0"+secondStr;
    timerTextView.setText(Integer.toString(min)+":"+secondStr);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         timerSeekBar=findViewById(R.id.timerSeekBar);
        timerTextView=findViewById(R.id.countdownTextView);
        goButton=findViewById(R.id.GoButton);
        timerSeekBar.setMax(600);
        timerSeekBar.setProgress(30);
        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
               updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}