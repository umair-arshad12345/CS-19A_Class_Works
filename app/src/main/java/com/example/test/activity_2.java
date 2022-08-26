package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class activity_2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        Intent i = getIntent();
        if(i.hasExtra("data")){
            String msg = i.getStringExtra("data");
            TextView t = findViewById(R.id.text);
            t.setText(msg);
        }
        Button b = findViewById(R.id.back);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("data","Message from activity 2");
                setResult(RESULT_OK,returnIntent);
                finish();
            }
        });

    }
}