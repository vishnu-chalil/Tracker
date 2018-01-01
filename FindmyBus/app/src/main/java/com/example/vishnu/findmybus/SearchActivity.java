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
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {
    EditText Busnumber;
    public boolean find;
    public int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Busnumber = findViewById(R.id.NUM);

        GetBusNos getBusNos = new GetBusNos(SearchActivity.this);
        getBusNos.getBusnos();
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
        else {i = 0;
            while (i < Global.bus_nos.size()) {
                if (Integer.valueOf(Global.busnum) == Global.bus_nos.get(i)) {
                    find = true;
                    break;
                } else {
                    find = false;
                }
                i++;
            }

            if (find) {
                FindfromView findfromView = new FindfromView(SearchActivity.this);
                findfromView.findLoc();
                Intent intent = new Intent(SearchActivity.this, Track.class);
                SearchActivity.this.startActivity(intent);
            } else {
                Toast.makeText(SearchActivity.this,"Bus Not Available Today", Toast.LENGTH_SHORT).show();
            }

        }



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case R.id.settings:
                return true;
            case R.id.signout:
                SharedPreferences preferences = getSharedPreferences("PREFS", MODE_PRIVATE);
                final SharedPreferences.Editor editor = preferences.edit();
                editor.remove("email");
                editor.apply();
                Intent started = new Intent(SearchActivity.this, MainActivity.class);
                SearchActivity.this.startActivity(started);
                return true;
            default:
                return super.onOptionsItemSelected(item);
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
