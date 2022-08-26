package com.example.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.test.MyAdapters.ProductAdapter;
import com.example.test.MyFragments.add_product_fragment;
import com.example.test.MyServices.MyService;
import com.example.test.myclasses.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Products extends AppCompatActivity {


    ListView l;
    static ArrayList<Product> productsArrayList = new ArrayList<>();;


    void LoadView(){
        ProductAdapter productAdapter = new ProductAdapter(productsArrayList,this,R.layout.product_list_view);
        l.setAdapter(productAdapter);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);


        Intent i = new Intent(this, MyService.class);
        startService(i);

        Product product = new Product();
        product.title = "Laptop";
        product.Price = "$100";
        product.description = "Some Description";

        productsArrayList.add(product);

        l = findViewById(R.id.product_listview);
        LoadView();

        l.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder b = new AlertDialog.Builder(Products.this);
                b.setMessage("Do you want to remove this item");
                b.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            productsArrayList.remove(position);
                            LoadView();
                    }
                });
                b.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                    }
                });
                b.create().show();
                return false;
            }
        });
        FloatingActionButton addbtn = findViewById(R.id.addproductbtn);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_product_fragment product_fragment = new add_product_fragment();
                product_fragment.show(getSupportFragmentManager(),null);
               View myview =  LayoutInflater.from(Products.this).inflate(R.layout.fragment_add_product_fragment,null,false);
                AlertDialog.Builder b = new AlertDialog.Builder(Products.this);
                b.setView(myview);
                b.create().show();
                Button btn = myview.findViewById(R.id.addbtn);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText title = myview.findViewById(R.id.title);
                        EditText description = myview.findViewById(R.id.description);
                        EditText price = myview.findViewById(R.id.price);

                        Product p =new Product();
                        p.description = description.getText().toString();
                        p.title = title.getText().toString();
                        p.Price = price.getText().toString();

                        productsArrayList.add(p);
                        LoadView();
                    }
                });
            }
        });

    }
}