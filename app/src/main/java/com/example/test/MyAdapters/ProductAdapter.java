package com.example.test.MyAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.test.R;
import com.example.test.myclasses.Product;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {

    ArrayList<Product> products;
    LayoutInflater inflater;
    Context context;
    int viewid;

    public ProductAdapter(ArrayList<Product> data, Context ctx, int viewid){
        products = data;
        context = ctx;
        this.viewid = viewid;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(viewid,null,false);

        TextView title = v.findViewById(R.id.title);
        TextView description = v.findViewById(R.id.description);
        TextView price = v.findViewById(R.id.price);

        title.setText(products.get(position).title);
        description.setText(products.get(position).description);
        price.setText(products.get(position).Price);

        return v;
    }


}
