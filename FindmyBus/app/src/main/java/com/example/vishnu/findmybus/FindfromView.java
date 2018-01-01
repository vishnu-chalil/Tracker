package com.example.vishnu.findmybus;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vishnu on 30/12/17.
 */

public class FindfromView {

    private Context mContext;
    private String json_url ="http://192.168.43.230/getLatLon.php";


    public FindfromView(Context mContext) {


        this.mContext = mContext;
    }


public void findLoc(){


    CustomRequest jso = new CustomRequest(Request.Method.POST, json_url,  null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {

            JSONObject jsonObject = null;
            try {


                Global.lattitude = Double.valueOf(response.getString("lattitude"));
                Global.longitude = Double.valueOf(response.getString("longitude"));
                Global.status = Integer.parseInt(response.getString("available"));
                Toast.makeText(mContext,"Bus No " + Global.busnum,Toast.LENGTH_LONG).show();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }}, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(mContext,"Couldn't connect",Toast.LENGTH_SHORT).show();
        }
    }){ @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("email", Global.busnum);

        return parameters;
    }

    };

    Mysingleton.getmInstance(mContext).addToRequestque(jso);

    }
}
