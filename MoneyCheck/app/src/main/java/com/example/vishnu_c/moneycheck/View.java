package com.example.vishnu_c.moneycheck;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class View extends AppCompatActivity {
    ListView mylsit1;
    String json_url="http://192.168.122.1/getinfo.php";
    int sum =0;
    TextView t;
    String email = Global.tabname;

    String [] list1 = new String[20];
    String [] list2 = new String[20];
    String [] list3 = new String[20];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Arrays.fill(list1, " ");
        Arrays.fill(list2, " ");
        Arrays.fill(list3, " ");


        mylsit1 = (ListView) findViewById(R.id.list1);
        t = (TextView) findViewById(R.id.out);




        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.POST, json_url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(View.this, email, Toast.LENGTH_SHORT).show();

                int a;
                String b,c,d;


                int count = 0 , point = 0;
                while (count < response.length()) {

                    JSONObject jsonObject = null;
                    try {
                        jsonObject = response.getJSONObject(count);
                        a = Integer.parseInt(jsonObject.getString("amount"));
                        b = (jsonObject.getString("remarks"));
                        c = jsonObject.getString("email");
                        d= jsonObject.getString("transdate");
                        if(c.contains(email)) {
                            list1[point] = a + "     " + b + "      "+d;
                            point++;
                            sum = sum + a;

                            if (count == 19)
                                break;

                        }
                        count++;
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                t.setText("Total Amount : " + sum);



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(View.this, "Error...", Toast.LENGTH_SHORT).show();
                error.printStackTrace();

            }
        })  ;
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list1);
        mylsit1.setAdapter(adapter1);

        Mysingleton.getmInstance(View.this).addToRequestque(jsonArrayRequest);
    }

    }







