package com.brindha.gui;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextSwitcher mTextSwitcher;
    private int position = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextSwitcher = findViewById(R.id.textSwitcher);
        Button previous = findViewById(R.id.button3);
        Button next = findViewById(R.id.button);

        final String[] text = {"Smriti Shriniwas Mandhana is an Indian cricketer 🏏 ",
        "Born 🎂: 18 July 1996 (age 25 years), Mumbai🏢","👉🏻 Left hand bat\n"+ "👉🏻 Right arm offbreak and Opening batter"};


        mTextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                // create textView
                TextView textView = new TextView(getApplicationContext());
                textView.setTextSize(17);
                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.parseColor("#e1d384"));
                return textView;
            }
        });


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position < text.length - 1) {
                    position += 1; //position = position + 1;
                    mTextSwitcher.setText(text[position]);
                }


            }
        });

        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (position > 0) {
                    position -= 1; // position = position - 1;
                    mTextSwitcher.setText(text[position]);
                }


            }
        });

    }

}