package com.example.vishnu.findmybus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class SearchActivity extends AppCompatActivity {
    EditText Busnumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Busnumber = findViewById(R.id.NUM);



    }

        public void getList(View view) {
        Intent started = new Intent(SearchActivity.this, GetList.class);
        SearchActivity.this.startActivity(started);

    }

    public void Find(View view) {

        Global.busnum = Busnumber.getText().toString();
        FindfromView findfromView = new FindfromView(SearchActivity.this);
        findfromView.findLoc();
            Intent intent = new Intent(SearchActivity.this, Track.class);
            SearchActivity.this.startActivity(intent);
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
    }
