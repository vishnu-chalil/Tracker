package com.example.vishnu.findmybus;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
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
 * Created by vishnu on 14/1/18.
 */

public class GetIndividual {

    private Context mContext;
    private String json_url;
    ProgressDialog progressDialog;

    View view;

    public GetIndividual(Context mContext, String json_url, View view) {
        this.mContext = mContext;
        this.json_url = json_url;
        this.view = view;
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

                JSONObject jsonObject = null;
                try {


                    Global.lattitude = Double.valueOf(response.getString("lattitude"));
                    Global.longitude = Double.valueOf(response.getString("longitude"));
                    Global.status = Integer.parseInt(response.getString("status"));
                    if(Global.status == 1){
                       Toast.makeText(mContext,"Bus No " + Global.busnum ,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(mContext, Track.class);
                    mContext.startActivity(intent);
                    }else
                        Toast.makeText(mContext,"Bus not available" ,Toast.LENGTH_LONG).show();




                } catch (JSONException e) {
                    progressDialog.dismiss();

                    e.printStackTrace();

                }

            }}, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Snackbar
                        .make(view, "No network connection.",Snackbar.LENGTH_LONG)
                        .show();


            }
        }){ @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> parameters = new HashMap<>();
            parameters.put("busnum", Global.busnum);

            return parameters;
        }

        };

        Mysingleton.getmInstance(mContext).addToRequestque(jso);

    }
}
