package com.example.vishnu_c.portal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText Email;
    EditText Password;
    TextView signup;
    Button login;

    String server_url = "http://192.168.122.1/serve.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Email = (EditText) findViewById(R.id.tv1);
        Password = (EditText) findViewById(R.id.tv2) ;
        signup = (TextView) findViewById(R.id.signup);
        login = (Button) findViewById(R.id.login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String password,email;

                email = Email.getText().toString();
                password = Password.getText().toString();




                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.length() > 2) {
                  //          Intent started = new Intent(MainActivity.this,Starting.class);
                            Global.tabname = email;
                   //         MainActivity.this.startActivity(started);

                        }


                        else {

                            Toast.makeText(MainActivity.this,"Wrong user Email or Password ",Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(MainActivity.this,"Error occured",Toast.LENGTH_SHORT).show();


                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        {
                            Map<String, String> params = new HashMap<String, String>();


                            params.put("email", email);

                            params.put("password", password);


                            return params;
                        }
                    }

                };





                Mysingleton.getmInstance(MainActivity.this).addToRequestque(stringRequest);

            }



        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,SignUp.class);
                MainActivity.this.startActivity(intent);

            }
        });
    }



}
