package com.example.user.gps_service;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2/21/2018.
 */

public class Senderlog {
    public Senderlog(Context context)
    {
        this.context = context;
    }

    public Context context;
    String server_url = "http://samplewebsiteone.000webhostapp.com/login.php";

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
