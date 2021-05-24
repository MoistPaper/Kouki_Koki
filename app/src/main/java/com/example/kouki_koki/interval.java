package com.example.kouki;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class interval extends AppCompatActivity {
    private int minute;
    private int seconds;
    private int session=0;
    private NumberPicker numberPickerMin;
    private NumberPicker numberPickerSec;
    private int[] sesh;
    private int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interval);
        Intent intent = getIntent();
        session = intent.getIntExtra("session",0);
        sesh= new int[session];
        numberPickerMin = (NumberPicker) findViewById(R.id.numberPickerMin);
        numberPickerSec = (NumberPicker) findViewById(R.id.numberPickerSec);
        numberPickerMin.setMaxValue(59);
        numberPickerMin.setMinValue(0);
        numberPickerSec.setMaxValue(59);
        numberPickerSec.setMinValue(0);
        numberPickerSec.setValue(0);
        numberPickerMin.setValue(0);
        numberPickerMin.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1)
            {
                minute= i1;
            }
        });
        numberPickerSec.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1)
            {
                seconds=i1;
            }
        });

    }
    public void onset(View v){
        if(index<session){
            seconds+=minute*60;
            sesh[index]=seconds;
            index++;
            Toast.makeText(this, "Session "+index+" set", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "All sessions set! Please click start.", Toast.LENGTH_SHORT).show();
        }
    }
    public void startTimer(View v){
        if(index>=session){
            Intent intent = new Intent(this, Timer_Activity.class);
            intent.putExtra("interval",sesh);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Please finish setting session intervals", Toast.LENGTH_SHORT).show();
        }
    }
}