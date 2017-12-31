package com.example.vishnu_c.moneycheck;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Transaction extends AppCompatActivity {

    String tabname,server_url="http://192.168.122.1//logfile.php";

    String amount,remarks;

    EditText Amount,Remarks;

    Button upload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);


        Amount = (EditText) findViewById(R.id.amount);
        Remarks = (EditText) findViewById(R.id.remarks);

        upload = (Button) findViewById(R.id.submit);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd-HH:mm:ss");
        Date date = new Date();
        final String transdate = dateFormat.format(date);


       this.tabname = Global.tabname;

       upload.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {


           amount= Amount.getText().toString();

           remarks = Remarks.getText().toString();
           if(amount.length() < 1 || remarks.length() <1){

               Toast.makeText(Transaction.this,"Amount and remarks cannot be empty",Toast.LENGTH_SHORT).show();
           }


          else {

               StringRequest stringRequest = new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
                   @Override
                   public void onResponse(String response) {

                       if (response.length() > 2) {


                           Toast.makeText(Transaction.this, "Transaction Successfull", Toast.LENGTH_SHORT).show();


                       } else {

                           Toast.makeText(Transaction.this, "Failed ", Toast.LENGTH_SHORT).show();
                       }

                   }


               }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {

                       Toast.makeText(Transaction.this, "Error occured", Toast.LENGTH_SHORT).show();


                   }
               }) {

                   @Override
                   protected Map<String, String> getParams() throws AuthFailureError {

                       {
                           Map<String, String> params = new HashMap<String, String>();
                           params.put("email", tabname);
                           params.put("amount", amount);
                           params.put("remarks", remarks);
                           params.put("transdate", transdate);


                           return params;
                       }
                   }

               };


               Mysingleton.getmInstance(Transaction.this).addToRequestque(stringRequest);

           }
       }
   });

    }
    }

