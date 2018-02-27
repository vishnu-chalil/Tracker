package com.example.vishnu.findmybus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class GetList extends AppCompatActivity {

    String json_url="https://samplewebsiteone.000webhostapp.com/sendbuslist.php";
    private ListView  Ivproduct;
    private ProductListAdapter adapter;
    private List<Product> mProductList;
    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_list);
        Ivproduct = findViewById(R.id.mainview);
        mProductList = new ArrayList<>();
        progressDialog = new ProgressDialog(GetList.this);

        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Please wait");
        progressDialog.show();
        progressDialog.setCancelable(true);





        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, json_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                int count = 0;

                while (count < response.length()) {

                    JSONObject jsonObject = null;
                    try {

                        jsonObject = response.getJSONObject(count);
                        int a = Integer.parseInt(jsonObject.getString("bus_no"));
                        String point1 = jsonObject.getString("point1");
                        String point2 = jsonObject.getString("point2");
                        mProductList.add(new Product(a, a + "", point1, point2));

                     count++;

                    } catch (JSONException e) {
                        e.printStackTrace();
                        count++;
                    }


                }

                progressDialog.dismiss();


                adapter = new ProductListAdapter(getApplicationContext(),mProductList);
                Ivproduct.setAdapter(adapter);
                Ivproduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Global.busnum = String.valueOf(view.getTag());
                        FindfromView findfromView = new FindfromView(GetList.this,Search.json_url1);
                        findfromView.findLoc();



                    }
                });


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();


                Toast.makeText(GetList.this, "Check connectivity and try again", Toast.LENGTH_SHORT).show();

            }
        })  ;
        Mysingleton.getmInstance(GetList.this).addToRequestque(jsonArrayRequest);


    }


}







