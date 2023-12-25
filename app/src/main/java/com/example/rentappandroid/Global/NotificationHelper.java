package com.example.rentappandroid.Global;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import com.example.rentappandroid.R;

import android.app.Notification;


public class NotificationHelper {

    private static final String CHANNEL_ID = "your_channel_id"; // Đặt một ID cho channel
    private static final int NOTIFICATION_ID = 1; // ID cho thông báo

    public static void showNotification(Context context, String title, String content) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Your Channel Name",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            notificationManager.createNotificationChannel(channel);
        }

        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.notification);

        Notification notification = new Notification.Builder(context, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(R.drawable.post)
                .setLargeIcon(largeIcon)
                .build();

        notificationManager.notify(NOTIFICATION_ID, notification);
    }
}
