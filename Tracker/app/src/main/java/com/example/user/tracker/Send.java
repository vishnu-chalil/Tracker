package com.example.user.tracker;

import com.android.volley.AuthFailureError;

import com.android.volley.Request;

import com.android.volley.Response;

import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;


import java.util.HashMap;
import java.util.Map;


import android.content.Context;
import android.widget.Toast;

/**
 * Created by user on 1/7/2018.
 */

public class Send {

    public Send(Context context) {
        this.context = context;
    }

    public Context context;
    String email;
    String password;
    String server_url = "http://192.168.43.170/location.php";

    public void razmil() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.length() > 2) {
                    Toast.makeText(context, "ponundeeeee", Toast.LENGTH_SHORT).show();

                } 
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(context, "Error occured", Toast.LENGTH_SHORT).show();


            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                {
                    Map<String, String> params = new HashMap<>();

                    params.put("lattitude", String.valueOf(Global.lattitude));
                    params.put("longitude", String.valueOf(Global.longitude));
                    params.put("bus_no",Global.a);

                    return params;
                }
            }


        };

              Mysingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

}