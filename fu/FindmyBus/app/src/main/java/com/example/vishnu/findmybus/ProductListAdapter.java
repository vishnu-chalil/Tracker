package com.example.vishnu.findmybus;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.vishnu.findmybus.Product;
import com.example.vishnu.findmybus.R;

import java.util.List;

/**
 * Created by vishnu on 30/12/17.
 */

public class ProductListAdapter extends BaseAdapter {

    private Context mContext;
    private List<Product> mProductList;

    public ProductListAdapter(Context mContext, List<Product> mProductList) {
        this.mContext = mContext;
        this.mProductList = mProductList;
    }

    @Override
    public int getCount() {
        return mProductList.size();
    }

    @Override
    public Object getItem(int i) {
        return mProductList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v =View.inflate(mContext, R.layout.item_product_list,null);
        TextView tv1 =(TextView)v.findViewById(R.id.tv1);
        TextView tv2 =(TextView)v.findViewById(R.id.tv2);
        TextView tv3 =(TextView)v.findViewById(R.id.tv3);

        tv1.setText(mProductList.get(i).getName());
        tv2.setText(String.valueOf(mProductList.get(i).getPrice()));
        tv3.setText(mProductList.get(i).getDescription());

        v.setTag(mProductList.get(i).getId());


        return v;
    }
}
