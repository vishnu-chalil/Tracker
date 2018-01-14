package com.example.vishnu.findmybus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {
    EditText Busnumber;
    TextView tv;
    public static String json_url1 ="https://samplewebsiteone.000webhostapp.com/getLatLon.php";
    String json_url2 ="https://samplewebsiteone.000webhostapp.com/Getbusnos.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Busnumber = findViewById(R.id.NUM);
        tv = findViewById(R.id.sign_out);
        final  View view = findViewById(android.R.id.content);


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences = getSharedPreferences("PREFS", MODE_PRIVATE);
                final SharedPreferences.Editor editor = preferences.edit();
                editor.remove("email");
                editor.apply();
                Intent started = new Intent(SearchActivity.this, MainActivity.class);
                SearchActivity.this.startActivity(started);
            }
        });
    }

        public void getList(View view) {
        Intent started = new Intent(SearchActivity.this, GetList.class);
        SearchActivity.this.startActivity(started);
    }



    public void Find(View view) {
        Global.busnum = Busnumber.getText().toString();

        if (Global.busnum.matches("")) {
            Toast.makeText(SearchActivity.this,"Enter a Bus number",Toast.LENGTH_SHORT).show();
        }
        else {
          GetIndividual getIndividual = new GetIndividual(SearchActivity.this, json_url2,view);
          getIndividual.findLoc();
        }
    }

    private boolean doubleBackToExitPressedOnce;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                finishAffinity();
            }
            System.exit(0);
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
}
