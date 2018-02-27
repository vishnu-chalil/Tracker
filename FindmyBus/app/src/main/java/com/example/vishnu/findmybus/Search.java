package com.example.vishnu.findmybus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Search extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    AutoCompleteTextView place;
    EditText Busnumber;
    TextView tv;
    ProgressDialog progressDialog;

    public static String json_url1 ="https://samplewebsiteone.000webhostapp.com/getLatLon.php";
    String json_url2 ="https://samplewebsiteone.000webhostapp.com/Getbusnos.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressDialog = new ProgressDialog(Search.this);
        Busnumber = findViewById(R.id.NUM);
        tv = findViewById(R.id.sign_out);
        final  View view = findViewById(android.R.id.content);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public void getList(View view) {
        Intent started = new Intent(Search.this, GetList.class);
        Search.this.startActivity(started);
    }
    public void Find(View view) {

        Global.busnum = Busnumber.getText().toString();

        if (Global.busnum.matches("")) {

            Toast.makeText(Search.this,"Enter a Bus number",Toast.LENGTH_SHORT).show();
        }
        else {

            GetIndividual getIndividual = new GetIndividual(Search.this, json_url2,view);
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




    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.sign_out) {

            SharedPreferences preferences = getSharedPreferences("PREFS", MODE_PRIVATE);
            final SharedPreferences.Editor editor = preferences.edit();
            editor.remove("email");
            editor.apply();
            Intent started = new Intent(Search.this, MainActivity.class);
            Search.this.startActivity(started);
        } else if (id == R.id.about) {
            Intent intent = new Intent(Search.this,About.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
