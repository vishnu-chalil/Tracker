package com.example.vishnu.offline;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper mydb;
    EditText name;
    EditText surname;
    EditText marks;
    Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DataBaseHelper(this);

         name =(EditText) findViewById(R.id.editText_name);
         surname =(EditText) findViewById(R.id.editText_surname);
         marks =(EditText) findViewById(R.id.editText_marks);
        bt = (Button) findViewById(R.id.button_insert);

        AddData();
    }

    public void AddData(){
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isinserted = mydb.insertdata(name.getText().toString(),surname.getText().toString(),marks.getText().toString());

                if((isinserted)){
                    Toast.makeText(MainActivity.this,"Data inserted",Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
