package com.example.kouki_koki;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //change activity when button is pressed
        click = findViewById(R.id.click);
        Intent myIntent = new Intent(MainActivity.this, Timer_Activity.class);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "My_notification");
                builder.setSmallIcon(R.drawable.notif_icon);
                builder.setContentTitle("Get ready to start!");
                builder.setContentText("Get your ass into the app my dude");
                //builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);
                managerCompat.notify(1, builder.build());
                */


                MainActivity.this.startActivity(myIntent);
            }
        });




    }


    }


