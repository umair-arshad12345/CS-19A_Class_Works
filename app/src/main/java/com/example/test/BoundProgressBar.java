package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.telecom.ConnectionService;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.test.MyServices.BoundService;
import com.example.test.myclasses.Firebase;
import com.example.test.myclasses.Firestore;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Connection;
import java.util.HashMap;

public class BoundProgressBar extends AppCompatActivity {

    BoundService serviceRef;
    ProgressBar progressBar;
    TextView progress;
    Button start,stop,pause;

//    Firebase fb = new Firebase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bound_progress_bar);


        Firestore store = new Firestore(this);
        HashMap<String,Object> product = new HashMap<>();
        product.put("id",1);
        product.put("name","LCD");
        product.put("price","500$");
//        store.Add("product",product);

//        store.Update("product","sxSQNF1HWVgy513usPsQ",product);
//        store.Read("product").addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//            @Override
//            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                    for(DocumentSnapshot document: queryDocumentSnapshots){
//                        System.out.println(document.getId().toString());
//                        System.out.println(document.get("id").toString());
//                        System.out.println(document.get("name").toString());
//                        System.out.println(document.get("price").toString());
//                    }
//            }
//        });
        store.filter("product","name","LCD").addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(DocumentSnapshot document: queryDocumentSnapshots){
                        System.out.println(document.getId().toString());
                        System.out.println(document.get("id").toString());
                        System.out.println(document.get("name").toString());
                        System.out.println(document.get("price").toString());
                    }
            }
        });

        store.Delete("product","sxSQNF1HWVgy513usPsQ");



//        fb.Signup("shahan_arif@yahoo.com","shahan");
        progress = findViewById(R.id.text);
        progressBar = findViewById(R.id.progressBar);
        start = findViewById(R.id.start);
        stop = findViewById(R.id.stop);
        pause = findViewById(R.id.pause);

        ServiceConnection connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                BoundService.ProgressBinder b = (BoundService.ProgressBinder) service;
                serviceRef = b.getServiceContext();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                serviceRef = null;
            }
        };



        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(BoundProgressBar.this,BoundService.class);
                bindService(i,connection,BIND_AUTO_CREATE);
                Runnable r = new Runnable() {
                    @Override
                    public void run() {
                        serviceRef.start_progress(progressBar,progress);
                    }
                };
                new Handler().postDelayed(r,1000);
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String text = pause.getText().toString();
                    pause.setText((text.equals("pause"))? "resume" : "pause");
                    if(text.equals("pause")) {
                    serviceRef.SetPause(true);
                }else{
                        serviceRef.SetPause(false);
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService(connection);
            }
        });
    }
}