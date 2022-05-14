package com.emileferreira.bindasapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button buttonTwo = findViewById(R.id.button2);

        editText = findViewById(R.id.editText);


        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text = editText.getText().toString();

                if (text.equals("")) {
                    editText.requestFocus();
                    Toast.makeText(MainActivity.this, "Enter some Text", Toast.LENGTH_SHORT).show();
                } else {

                    try {
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, text);
                        sendIntent.setType("text/plain");
                        sendIntent.setPackage("com.whatsapp");
                        startActivity(sendIntent);
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Whatsapp not Installed", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });


    }
}