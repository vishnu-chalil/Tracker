package com.example.vishnu.findmybus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    EditText Name;
    EditText Email;
    EditText Password;
    EditText StudentID;
    EditText Username;
    Button signup;

    String server_url = "http://192.168.43.230/adduser.php";

    AlertDialog.Builder builder;
    String regex = "^[A-Za-z0-9+_.-]+@(.+)$";

    Pattern pattern = Pattern.compile(regex);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Name =  findViewById(R.id.textView4);
        Email =  findViewById(R.id.textView5);
        Password = findViewById(R.id.textView7);
        StudentID =  findViewById(R.id.textView6);
        Username =  findViewById(R.id.textView);
        signup =  findViewById(R.id.button2);
        builder = new AlertDialog.Builder(SignUp.this);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String name,email,password,studid,username;

                name = Name.getText().toString();

                email = Email.getText().toString();

                password = Password.getText().toString();

                username = Username.getText().toString();

                studid = StudentID.getText().toString();

                Matcher matcher = pattern.matcher(email);

                if(!matcher.matches()){

                    Toast.makeText(SignUp.this,"Enter valid email id",Toast.LENGTH_SHORT).show();
                }else {


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

                            Toast.makeText(SignUp.this, "Error...", Toast.LENGTH_SHORT).show();
                            error.printStackTrace();

                        }
                    }) {

                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();

                            params.put("studid", studid);
                            params.put("name", name);
                            params.put("username",username);
                            params.put("email", email);
                            params.put("passwd", password);

                            return params;
                        }
                    };


                    Mysingleton.getmInstance(SignUp.this).addToRequestque(stringRequest);

                }
            }

        });



    }
}
