package com.example.user.gps_service;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    ProgressDialog p;
    Button button;
    EditText editText,editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button = (Button)findViewById(R.id.button);
        editText = (EditText)findViewById(R.id.editText);
        editText2=(EditText)findViewById(R.id.editText2);
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startService(v);
        }
    });
    }
    public void startService(View view) {

        Global.a=editText.getText().toString();
        Global.b=editText2.getText().toString();
        Senderlog senderlog = new Senderlog(Login.this);
        senderlog.razmil();
        if (Global.in){

            Global.in  = false;
            Global.x=1;
            Intent intent = new Intent(Login.this,MainActivity.class);
            startActivity(intent);

        }
        else{
            Toast.makeText(this, "false aanu", Toast.LENGTH_SHORT).show();
        }
    }


    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                finishAffinity();
            }
            System.exit(0);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }


}
