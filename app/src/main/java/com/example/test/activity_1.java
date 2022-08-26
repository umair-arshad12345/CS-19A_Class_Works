package com.example.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.xml.transform.Result;

public class activity_1 extends AppCompatActivity {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == MyRequestCode && resultCode == RESULT_OK && data != null){
            if(data.hasExtra("data")){
                String msg = data.getStringExtra("data");
                TextView t = findViewById(R.id.text);
                t.setText(msg);
            }
        }
    }

    int MyRequestCode = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_1);

        Button b = findViewById(R.id.button1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity_1.this,activity_2.class);
                i.putExtra("data","message from activity 1");
                startActivityForResult(i,MyRequestCode);
            }
        });
    }
}