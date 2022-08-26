package com.example.test;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.test.MyServices.MyService;

public class ProgressBarActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        ProgressBar progressBar = findViewById(R.id.progressBar);
        TextView progress = findViewById(R.id.text);
        Button start = findViewById(R.id.start);
        Button stop = findViewById(R.id.stop);
        Intent i = new Intent(ProgressBarActivity.this, MyService.class);

        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Integer data = intent.getIntExtra("progress",0);
                progress.setText(data.toString());
                progressBar.setProgress(data);
            }
        };
        this.registerReceiver(receiver,new IntentFilter("UpdateProgress"),0);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(i);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(i);
            }
        });
    }

}