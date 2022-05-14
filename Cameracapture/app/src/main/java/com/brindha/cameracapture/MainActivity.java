package com.brindha.cameracapture;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import static android.Manifest.permission.CAMERA;

public class MainActivity extends AppCompatActivity {


    private static final int CAMERA_REQUEST = 1;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonCapture = findViewById(R.id.button);
        imageView = findViewById(R.id.imageView);


        buttonCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // check permission
                if (ContextCompat.checkSelfPermission(getApplicationContext(), CAMERA) ==
                        PackageManager.PERMISSION_GRANTED) {

                    // permission granted
                    // continue the action
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);


                } else {
                    // permission not granted
                    // ask for the permission
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{CAMERA}, 1);

                }


            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED) {

                    // permission granted
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);


                } else {
                    // permission denied
                    Toast.makeText(this, "You cannot buttonCapture photo without permission", Toast.LENGTH_SHORT).show();
                }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST) {
            if (resultCode == Activity.RESULT_OK) {

                Bitmap photo = (Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(photo);

            }

        }
    }


}