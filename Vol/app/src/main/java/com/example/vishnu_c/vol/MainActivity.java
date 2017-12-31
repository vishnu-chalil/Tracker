package com.example.vishnu_c.vol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView1,textView2,textView3;

    String server_url = "http://192.168.122.1/serve.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button =(Button) findViewById(R.id.bn);
        textView1 = (TextView) findViewById(R.id.txt1);
        textView2 = (TextView) findViewById(R.id.txt2);
        textView3 = (TextView) findViewById(R.id.txt3);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, server_url,  null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                            textView1.setText(response.getString("Name"));
                            textView2.setText(response.getString("Email"));
                            textView3.setText(response.getString("Mobile"));

                        }catch (JSONException e)
                        {
                            e.printStackTrace();
                    }
                }}, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        textView1.setText("Error Occured");


                    }
                });
                    Mysingleton.getmInstance(getApplicationContext()).addToRequestque(jsonObjectRequest);
            }
        });




    }
}
