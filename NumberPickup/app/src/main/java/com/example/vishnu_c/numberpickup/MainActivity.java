package com.example.vishnu_c.numberpickup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.NumberPicker;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    NumberPicker numberPicker;
    NumberPicker numberPicker1;
    NumberPicker numberPicker2;
    String a,b,c;


    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView2);
        numberPicker = (NumberPicker) findViewById(R.id.numberPicker);
        numberPicker1 = (NumberPicker) findViewById(R.id.numberPicker2);
        numberPicker2 = (NumberPicker) findViewById(R.id.numberPicker3);


        numberPicker.setWrapSelectorWheel(true);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(12);

        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            /**
             * Called upon a change of the current value.
             *
             * @param picker The NumberPicker associated with this listener.
             * @param oldVal The previous value.
             * @param newVal The new value.
             */
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                 a = "/"+newVal;
            }
        });


        numberPicker1.setWrapSelectorWheel(true);
        numberPicker1.setMinValue(1);
        numberPicker1.setMaxValue(31);

        numberPicker1.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            /**
             * Called upon a change of the current value.
             *
             * @param picker The NumberPicker associated with this listener.
             * @param oldVal The previous value.
             * @param newVal The new value.
             */
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {

                b= "/"+newVal;
            }
        });

        numberPicker2.setWrapSelectorWheel(true);
        numberPicker2.setMinValue(1996);
        numberPicker2.setMaxValue(2017);

        numberPicker2.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            /**
             * Called upon a change of the current value.
             *
             * @param picker The NumberPicker associated with this listener.
             * @param oldVal The previous value.
             * @param newVal The new value.
             */
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                b = "/"+newVal;
                textView.setText(a+b+c);
            }
        });



    }
}
