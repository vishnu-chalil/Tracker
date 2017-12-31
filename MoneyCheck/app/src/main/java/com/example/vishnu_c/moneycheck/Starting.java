package com.example.vishnu_c.moneycheck;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Starting extends AppCompatActivity {


   ImageView imageView1;
   ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);

        imageView1 = (ImageView) findViewById(R.id.imageView2);
        imageView2 = (ImageView) findViewById(R.id.imageView);

        Toast.makeText(Starting.this, "Sucessfully logged in", Toast.LENGTH_LONG).show();
        Toast.makeText(Starting.this,Global.tabname,Toast.LENGTH_SHORT).show();

        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {

                Intent start1 = new Intent(Starting.this,Transaction.class);
                Starting.this.startActivity(start1);
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {

        Intent start1 = new Intent(Starting.this, com.example.vishnu_c.moneycheck.View.class);
        Starting.this.startActivity(start1);

        }
        });

        }
        }
