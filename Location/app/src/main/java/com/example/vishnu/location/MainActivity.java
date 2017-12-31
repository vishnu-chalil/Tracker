package com.example.vishnu.location;

import android.Manifest;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText t1;
    EditText t2;
    TextView textView;

    Button calc;

    float lat1;
    float lon1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        t1 = (EditText) findViewById(R.id.editText1);
        t2 = (EditText) findViewById(R.id.editText2);
        calc = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);


        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},123);
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(TextUtils.isEmpty(t1.getText()))
                {
                    lat1 = 0;
                }
                else
                {
                   lat1  = Float.parseFloat(t1.getText().toString());
                }

                if(TextUtils.isEmpty(t2.getText()))
                {
                    lon1 =0;
                }
                else
                {
                    lon1 = Float.parseFloat(t2.getText().toString());
                }


                float latt1 = (float) Math.toRadians(lat1);
                float lng1 = (float) Math.toRadians(lon1);


                GeoTracker tracker = new GeoTracker(getApplicationContext());

                Location loc = tracker.getLocation();

                if(loc !=null) {
                    float latt2 = (float) loc.getLatitude();
                    float lng2 = (float) loc.getLongitude();

                    textView.setText(latt1+""+lng1);

                }

            }
        });


        }

    }

