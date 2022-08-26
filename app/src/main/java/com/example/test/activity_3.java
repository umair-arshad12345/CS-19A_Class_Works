package com.example.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class activity_3 extends AppCompatActivity {
    int MyRequestCode =1;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);
        Button b=findViewById(R.id.button3);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(activity_3.this,activity_4.class);
                i.putExtra("data","Message from Activity 3");
                startActivityForResult(i,MyRequestCode);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == resultCode){
            TextView textView = findViewById(R.id.textView3);
            assert data != null;
            textView.setText(data.getStringExtra("activity4"));
        }
    }
}