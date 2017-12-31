package com.example.vishnu_c.portal;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    EditText Name;
    EditText Email;
    EditText Password;
    EditText Mobile;
    Button signup;

    String server_url = "http://192.168.122.1/adduser.php";

    AlertDialog.Builder builder;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Name = (EditText) findViewById(R.id.textView4);
        Email = (EditText) findViewById(R.id.textView5);
        Password = (EditText) findViewById(R.id.textView7);
        Mobile = (EditText) findViewById(R.id.textView6);
        signup = (Button) findViewById(R.id.button2);
        builder = new AlertDialog.Builder(SignUp.this);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name,email,password,mobile;

                name = Name.getText().toString();

                email = Email.getText().toString();

                password = Password.getText().toString();

                mobile = Mobile.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        builder.setTitle("Server Response");
                        builder.setMessage("Response :" + response);

                        builder.setPositiveButton("OK ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {




                            }
                        });




                        AlertDialog alertDialog = builder.create();

                        alertDialog.show();

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(SignUp.this,"Error...",Toast.LENGTH_SHORT).show();
                        error.printStackTrace();

                    }
                }){

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<String,String>();

                        params.put("name",name);
                        params.put("email",email);
                        params.put("mobile",mobile);
                        params.put("password",password);


                        return params;
                    }
                };


                Mysingleton.getmInstance(SignUp.this).addToRequestque(stringRequest);


            }

        });



    }
}
