package com.example.user.tracker;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


import java.util.HashMap;
import java.util.Map;


import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by user on 1/7/2018.
 */

public class Senderlog {

    public Senderlog(Context context) {
        this.context = context;
    }

    public Context context;
    String server_url = "http://192.168.43.170/login.php";

    public void razmil() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.length() > 2) {
                    Global.in= true;
                }
                else {
                    Global.in= false;
                    Toast.makeText(context, "wrong entries ", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, String.valueOf(error), Toast.LENGTH_SHORT).show();


            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                {
                    Map<String, String> params = new HashMap<>();

                    params.put("bus_no",Global.a);
                    params.put("bus_regno",Global.b);
                    return params;
                }
            }
        };

        Mysingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

}