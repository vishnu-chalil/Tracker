package com.example.vishnu.findmybus;


import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText Email;
    EditText Password;
    TextView signup;
    Button login;
    AlertDialog progressDialog;

    String server_url = "https://samplewebsiteone.000webhostapp.com/serve.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(MainActivity.this, R.style.Custom);



        SharedPreferences preferences = getSharedPreferences("PREFS",MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();
        String ispass = preferences.getString("email","" );
       if(ispass.length() >5  ){
            Intent started = new Intent(MainActivity.this,Search.class);
            MainActivity.this.startActivity(started);
        }


        Email =  findViewById(R.id.tv1);
        Password =  findViewById(R.id.tv2) ;
        signup =  findViewById(R.id.signup);
        login =  findViewById(R.id.login);

        final View view = findViewById(android.R.id.content);
        
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setTitle("Loading");
                progressDialog.setMessage("Please wait");
                progressDialog.show();
                progressDialog.setCancelable(false);
                progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));


                final String password,email;

                email = Email.getText().toString();
                password = Password.getText().toString();

                final String correctedemail = email.replaceAll("\\s+","");

                StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressDialog.dismiss();
                        if(response.length() > 2) {

                            editor.putString("email", correctedemail);
                            editor.putString("password", password);
                            editor.putBoolean("key",true);
                            editor.commit();
                            Intent started = new Intent(MainActivity.this,Search.class);
                            Global.tabname = email;
                            MainActivity.this.startActivity(started);
                        }

                        else {

                            Toast.makeText(MainActivity.this,"Wrong user Email or Password ",Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        //Toast.makeText(MainActivity.this,"Error occured",Toast.LENGTH_SHORT).show();
                        Snackbar
                                .make(view, "No network connection.",Snackbar.LENGTH_LONG)
                                .show();

                    }
                }) {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        {
                            Map<String, String> params = new HashMap<>();


                            params.put("email", correctedemail);

                            params.put("passwd", password);


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
