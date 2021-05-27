/*
This activity is created for the user to pick how many time intervals he/she wants
 */
package com.example.kouki_koki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    //Declare widgets and variables
    private NumberPicker numberPicker;
    private TextView textView;
    private int session = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textView= (TextView) findViewById(R.id.selected);
        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        //Set font
        Typeface quicksand=Typeface.createFromAsset(getAssets(),"fonts/Quicksand-Regular.ttf");
        textView.setTypeface(quicksand);
        //Set value for numberpickers
        numberPicker.setMaxValue(5);
        numberPicker.setMinValue(1);
        numberPicker.setValue(1);
        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1)
            {
                session=i1;
            }
        });
    }
    //Go to next activity
    public void start(View v){
        Intent intent = new Intent(this, Interval.class);
        intent.putExtra("session",session);
        startActivity(intent);
        finish();
    }
}
