package com.example.vishnu.findmybus;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
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
    private String json_url;
    AlertDialog.Builder builder;
    View view;
    private ProgressDialog progressDialog;


    public FindfromView(Context mContext, String json_url) {
        this.mContext = mContext;
        this.json_url = json_url;
    }

    public void findLoc(){

        progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please wait");
        progressDialog.show();
        progressDialog.setCancelable(false);

    CustomRequest jso = new CustomRequest(Request.Method.POST, json_url,  null, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            progressDialog.dismiss();

            //JSONObject jsonObject = null;
            try {


                Global.lattitude = Double.valueOf(response.getString("lattitude"));
                Global.longitude = Double.valueOf(response.getString("longitude"));
                Global.status = Integer.parseInt(response.getString("status"));

                Intent intent = new Intent(mContext, Track.class);
                mContext.startActivity(intent);

                Toast.makeText(mContext,"Bus No " + Global.busnum + "  " + Global.status,Toast.LENGTH_LONG).show();

            } catch (JSONException e) {
                progressDialog.dismiss();

                e.printStackTrace();

            }

        }}, new Response.ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError error) {
            progressDialog.dismiss();
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
