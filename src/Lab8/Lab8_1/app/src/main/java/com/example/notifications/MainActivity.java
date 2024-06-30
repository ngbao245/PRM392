package com.example.notifications;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.app.NotificationCompat;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppCompatButton btnSendNoti = findViewById(R.id.btnSendNotification);
        btnSendNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
                sendNotification(
                        "Title push notification",
                        "Message push notification",
                        R.drawable.ic_noti,
                        bitmap,
                        getResources().getColor(R.color.pink),
                        NotificationCompat.PRIORITY_DEFAULT);
            }
        });
    }

    private void sendNotification(String title, String message, int smallIcon, Bitmap bitmap, int color, int priority) {
        String uniqueChannelId = "notification_channel_" + getNotificationId();
        createNotificationChannel(uniqueChannelId);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, uniqueChannelId)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(smallIcon)
                .setLargeIcon(bitmap)
                .setColor(color)
                .setPriority(priority);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(getNotificationId(), builder.build());
        }
    }

    private int getNotificationId() {
        return (int) new Date().getTime();
    }

    private void createNotificationChannel(String channelId) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Channel name " + channelId;
            String description = "Channel description " + channelId;
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }
}
