package com.example.user.tracker;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Login extends AppCompatActivity {
ProgressDialog p;
    Button button;
    EditText editText,editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button=(Button)findViewById(R.id.button2);
        editText=(EditText)findViewById(R.id.editText);
        editText2=(EditText)findViewById(R.id.editText2);
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Global.a=editText.getText().toString();
            Global.b=editText2.getText().toString();
            Senderlog senderlog = new Senderlog(Login.this);
            senderlog.razmil();
           if (Global.in){
               Global.in= false;
               Intent intent=new Intent(Login.this,Sender.class);
                startActivity(intent);

            }
        }
    });
    }

}
