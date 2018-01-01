package com.example.vishnu.findmybus;

import android.content.Context;
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
    private String json_url ="http://192.168.43.230/Getbusnos.php";
    private Context context;

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

                Toast.makeText(context, "Error...", Toast.LENGTH_SHORT).show();

            }
        });
        Mysingleton.getmInstance(context).addToRequestque(jsonArrayRequest);

    }

}
