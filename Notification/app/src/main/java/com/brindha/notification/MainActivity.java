package com.brindha.notification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    private Button button;

    private final String CHANNEL_ID = "Cricket Latest Notification";

    private final int NOTIFICATION_ID = 01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                        .setSmallIcon(R.drawable.notification)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.notification))
                        .setContentTitle("Dhoni returns as Super Kings captain 🦁")
                        .setContentText("Jadeja hands back CSK captaincy to Dhoni")
                        .setStyle(new NotificationCompat.BigPictureStyle()
                                .bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.app_icon_foreground)))
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());

                //notificationId is a unique int for each notification that you must define
                notificationManager.notify(NOTIFICATION_ID, builder.build());

            }
        });
    }
}
