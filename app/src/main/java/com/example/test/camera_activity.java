package com.example.test;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class camera_activity extends AppCompatActivity {

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK && data != null){
            if(data.hasExtra("data")) {
                Bitmap b = (Bitmap) data.getExtras().get("data");
                ImageView i = findViewById(R.id.picture);
                i.setImageBitmap(b);
            }
        }

        if(requestCode == GALLEY_REQUEST && resultCode == RESULT_OK && data != null){
            if(data.getData() != null) {
               Uri d = data.getData();
                ImageView i = findViewById(R.id.picture);
                i.setImageURI(d);
            }
        }
    }

    Integer CAMERA_REQUEST = 1;
    Integer GALLEY_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        ImageView picture = findViewById(R.id.picture);
        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
//

                AlertDialog.Builder MediaAlert = new AlertDialog.Builder(camera_activity.this);
                MediaAlert.setMessage("Select Media");
                MediaAlert.setPositiveButton("Camera", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(i, CAMERA_REQUEST);
                    }
                });
                MediaAlert.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(i, GALLEY_REQUEST);
                    }
                });
                AlertDialog alert = MediaAlert.create();
                alert.show();
            }
        });
    }
}