package com.example.kouki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

public class home extends AppCompatActivity {
    private NumberPicker numberPicker;
    private TextView textView;
    private int session = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        textView= (TextView) findViewById(R.id.selected);
        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        Typeface quicksand=Typeface.createFromAsset(getAssets(),"fonts/quicksand.ttf");
        textView.setTypeface(quicksand);
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
    public void start(View v){
        Intent intent = new Intent(this, interval.class);
        intent.putExtra("session",session);
        startActivity(intent);
    }
}