/*
Timer activity runs timer until time maps are finished, plays music in the background
 */
package com.example.kouki_koki;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

import android.media.MediaPlayer;

public class TimerActivity extends AppCompatActivity implements View.OnClickListener {
    private Button buttonStartTime, buttonStopTime, returnToMenu;
    private TextView textViewShowTime;
    private CountDownTimer countDownTimer;
    private long totalTimeCountInMilliseconds;
    private Queue<Integer> timeMap = new LinkedList<>();
    private ProgressBar mProgressBar, mProgressBar1;
    private MediaPlayer player, player2;
    private Random ran = new Random();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        Intent intent = getIntent();
        int[] arr = intent.getIntArrayExtra("interval");
        //Converting array from previous activity into queue data type
        for(int x : arr){
            timeMap.add(x);
        }
        //Setting custom fonts
        Typeface MRegular = Typeface.createFromAsset(getAssets(),"fonts/Quicksand-Bold.ttf");
        //Link gui elements to code
        buttonStartTime = (Button) findViewById(R.id.button_timerview_start);
        buttonStopTime = (Button) findViewById(R.id.button_timerview_stop);
        returnToMenu = (Button) findViewById(R.id.ReturnToMenu);
        buttonStartTime.setTypeface(MRegular);
        buttonStopTime.setTypeface(MRegular);
        textViewShowTime = (TextView) findViewById(R.id.textView_timerview_time);
        buttonStartTime.setOnClickListener(this);
        buttonStopTime.setOnClickListener(this);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar_timerview);
        mProgressBar1 = (ProgressBar) findViewById(R.id.progressbar1_timerview);
        textViewShowTime.setTypeface(MRegular);
        returnToMenu.setVisibility(View.INVISIBLE);
        //Splitting song into two smaller players (so we can upload to github)
        player = MediaPlayer.create(this,R.raw.song);
        player2 = MediaPlayer.create(this, R.raw.song1);
        Intent intent2 = new Intent(this, MainActivity.class);

        //Returns to front page after timer is done (user clicks screen)
        returnToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlayer();
                startActivity(intent2);
                finish();
            }
        });
    }

    //Set actions for each button
    @Override
    public void onClick(View v) {
            if (v.getId() == R.id.button_timerview_start) {
                //Set timer interval, set button visibilities
                setTimer();
                buttonStartTime.setVisibility(View.INVISIBLE);
                buttonStopTime.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.INVISIBLE);
                //Play music, start timer
                play1();
                startTimer();
                mProgressBar1.setVisibility(View.VISIBLE);

            } else if (v.getId() == R.id.button_timerview_stop) {
                //Timer stop button and set button visibilities
                countDownTimer.cancel();
                countDownTimer.onFinish();
                mProgressBar1.setVisibility(View.GONE);
                mProgressBar.setVisibility(View.VISIBLE);
                buttonStartTime.setVisibility(View.VISIBLE);
                buttonStopTime.setVisibility(View.INVISIBLE);

            }
    }
    //Pulls timers from time map queue.
    //Continues to pull timers until there are no more left
    private void setTimer(){
        int time = 0;
        //If there are still elements, poll it and set it equal to time. Else, tell user there are no more timers
        if (!timeMap.isEmpty()) {
            time = timeMap.poll();
        } else Toast.makeText(TimerActivity.this, "Nothing left!", Toast.LENGTH_LONG).show();
        totalTimeCountInMilliseconds = time * 1000;
        mProgressBar1.setMax(time * 1000);

    }
    //Timer function, countdown timer that changes a circular progress bar according to time remaining
    private void startTimer() {

        countDownTimer = new CountDownTimer(totalTimeCountInMilliseconds, 1) {
            @Override
            //Sets progress bar in gui
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = leftTimeInMilliseconds / 1000;
                mProgressBar1.setProgress((int) (leftTimeInMilliseconds));
                textViewShowTime.setText(String.format("%02d", seconds / 60) + ":" + String.format("%02d", seconds % 60));
            }
            //Runs when timer completes
            @Override
            public void onFinish() {
                //Prepares next timer, sets timer face to next timer
                if(!timeMap.isEmpty()){
                    int minutes = timeMap.peek()/60, seconds = timeMap.peek() % 60;
                    if(minutes>=10){
                        if(seconds<10){
                            textViewShowTime.setText(minutes+":0"+seconds);
                        } else {
                            textViewShowTime.setText(minutes+":"+seconds);
                        }
                    } else {
                        if(seconds<10){
                            textViewShowTime.setText("0"+minutes+":0"+seconds);
                        } else {
                            textViewShowTime.setText("0"+minutes+":"+seconds);
                        }
                    }
                } else {
                    //If there are no more timers, tell user
                    textViewShowTime.setText("Done!");
                    returnToMenu.setVisibility(View.VISIBLE);
                    pause();
                }
                //Setting visibility for button and progress bars when timer finishes
                textViewShowTime.setVisibility(View.VISIBLE);
                buttonStartTime.setVisibility(View.VISIBLE);
                buttonStopTime.setVisibility(View.INVISIBLE);
                mProgressBar.setVisibility(View.VISIBLE);
                mProgressBar1.setVisibility(View.GONE);

            }

        }.start();
    }
    //Plays music file
    public void play1(){
        if(player == null){
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    //When first is complete, play second
                    play2();
                }
            });

        }

        player.start();
    }
    //Plays music file 2
    public void play2(){
        if(player2 == null){
            player2.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    //Stop player once file is done
                    stopPlayer();
                }
            });

        }

        player2.start();
    }

    //Pauses song, only does so if player has song playing
    private void pause(){
        if(player != null){
            player.pause();
        }
        if(player2 != null){
            player2.pause();
        }

    }
    //stops player if a song is on pause/is playing
    private void stopPlayer(){
        if(player != null){
            player.release();
            player = null;
        }
        if(player2 != null){
            player2.release();
            player2 = null;
        }

    }
    //java magic, need to do something in the superclass...
    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }



}
