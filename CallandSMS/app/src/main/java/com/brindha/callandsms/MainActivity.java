package com.brindha.callandsms;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button buttonCall = findViewById(R.id.button);
        Button buttonSms = findViewById(R.id.button2);

        EditText editText = findViewById(R.id.editText);


        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String number = editText.getText().toString().trim();

                if (!number.equals("")) {

                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + number));
                    startActivity(intent);

                } else {
                    Toast.makeText(MainActivity.this, "Please enter the Phone number to continue", Toast.LENGTH_SHORT).show();
                }


            }
        });

        buttonSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String number = editText.getText().toString().trim();

                if (!number.equals("")) {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("sms:" + number));
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Please enter the Phone number to continue", Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

}