package com.example.vishnu.customlistviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ListView  Ivproduct;
    private ProductListAdapter adapter;
    private List<Product> mProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Ivproduct = (ListView)findViewById(R.id.mainview);
        mProductList = new ArrayList<>();


        mProductList.add(new Product(1,"iphone4", "200","Apple iphone 64GB"));
        mProductList.add(new Product(1,"iphone4", "200","Apple iphone 64GB"));
        mProductList.add(new Product(1,"iphone4", "200","Apple iphone 64GB"));
        mProductList.add(new Product(1,"iphone4", "200","Apple iphone 64GB"));
        mProductList.add(new Product(1,"iphone4", "200","Apple iphone 64GB"));
        mProductList.add(new Product(1,"iphone4", "200","Apple iphone 64GB"));


        adapter = new ProductListAdapter(getApplicationContext(),mProductList);
        Ivproduct.setAdapter(adapter);

        Ivproduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"Clicked ProductID = " + view.getTag(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
