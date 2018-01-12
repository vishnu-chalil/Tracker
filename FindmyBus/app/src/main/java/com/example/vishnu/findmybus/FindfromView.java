package com.example.vishnu.findmybus;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
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
    private String json_url ="https://samplewebsiteone.000webhostapp.com/getLatLon.php";
    AlertDialog.Builder builder;



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
            builder = new AlertDialog.Builder(mContext);


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
