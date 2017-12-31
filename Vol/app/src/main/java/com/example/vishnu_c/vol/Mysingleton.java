package com.example.vishnu_c.vol;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by vishnu-c on 10/7/17.
 */

public class Mysingleton {


    private static Mysingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mctx;

    private Mysingleton(Context context) {

        mctx = context;
        requestQueue =  getRequestQue();

    }


    public RequestQueue getRequestQue(){

            if(requestQueue == null){

                requestQueue = Volley.newRequestQueue(mctx.getApplicationContext());

            }
        return  requestQueue;
    }

    public static synchronized Mysingleton getmInstance(Context context){

        if(mInstance == null){

            mInstance = new Mysingleton(context);

        }
        return mInstance;
    }

    public<T> void addToRequestque(Request<T> request){

        requestQueue.add(request);
    }
}
