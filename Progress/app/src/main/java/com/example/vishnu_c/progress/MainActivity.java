package com.example.vishnu_c.progress;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {



     ProgressDialog progressDialog;
     Timer t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressDialog = new ProgressDialog(MainActivity.this);



    }

    public  void buttonclicked(View view){

        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please wait");
        progressDialog.show();
        progressDialog.setCancelable(true);

        t.schedule(new TimerTask() {
            @Override
            public void run() {

                progressDialog.dismiss();
            }
        },5000);



    }
}
