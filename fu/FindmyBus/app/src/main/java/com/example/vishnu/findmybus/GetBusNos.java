package com.example.vishnu.findmybus;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.reflect.Array;

/**
 * Created by vishnu on 1/1/18.
 */

public class GetBusNos {
    private String json_url ="https://samplewebsiteone.000webhostapp.com/Getbusnos.php";
    private Context context;
    AlertDialog.Builder builder;


    public GetBusNos(Context context) {
        this.context = context;
    }

    public  void getBusnos() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, json_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int count = 0;
                while (count < response.length()) {

                    JSONObject jsonObject = null;
                    try {

                        jsonObject = response.getJSONObject(count);
                        int a = Integer.parseInt(jsonObject.getString("bus_no"));
                       Global.bus_nos.add(a);
                        count++;

                    } catch (JSONException e) {
                        e.printStackTrace();
                        count++;
                    }


                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                builder = new AlertDialog.Builder(context);


                builder.setTitle("Oops");
                builder.setMessage("Couldn't connect...make sure data is on and restart app");

                builder.setPositiveButton("OK ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });


                AlertDialog alertDialog = builder.create();

                alertDialog.show();
            }
        });
        Mysingleton.getmInstance(context).addToRequestque(jsonArrayRequest);

    }

}
