package com.example.vishnu.findmybus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    public void signout(View view) {
        SharedPreferences preferences = getSharedPreferences("PREFS", MODE_PRIVATE);
        final SharedPreferences.Editor editor = preferences.edit();
        editor.remove("email");
        editor.commit();
        Intent started = new Intent(SearchActivity.this, MainActivity.class);
        SearchActivity.this.startActivity(started);

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
}