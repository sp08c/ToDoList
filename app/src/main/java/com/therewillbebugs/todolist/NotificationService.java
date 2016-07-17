package com.therewillbebugs.todolist;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import java.util.Calendar;

public class NotificationService {
    private NotificationManager manager;
    private NotificationCompat.Builder builder;
    private AlarmManager alarmManager;
    private Context ctx;

    public NotificationService(Context ctx) {
        this.ctx = ctx;
        builder = new NotificationCompat.Builder(ctx);
        manager = (NotificationManager)ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        alarmManager = (AlarmManager)ctx.getSystemService(Context.ALARM_SERVICE);
    }

    public void createNotification(Task t) {
        // set required fields for notification
        // TODO: add proper icon, get team's opinion on notification contents
        Notification n = builder.setSmallIcon(R.drawable.ic_add_black)
            .setContentTitle(t.getDescription())
            .setContentText(t.getDateTimeString())
            .setPriority(Notification.PRIORITY_HIGH)
            .setVibrate(new long[0])
            .setAutoCancel(true)
            .build();

        // schedule notification for future if task has specified time
        long currentTime = Calendar.getInstance().getTimeInMillis();
        long taskTime = t.getScheduledTimeInMillis();
        if (taskTime > currentTime) {
            //scheduleNotification(n, taskTime - currentTime);
            //scheduleNotification(n, currentTime + 1000);
            manager.notify(0, n);
        } else {
            manager.notify(0, n);
        }
    }

    private void scheduleNotification(Notification n, long notifyDelayInMillis) {
        Intent notificationIntent = new Intent(ctx, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, n);
        PendingIntent pendingIntent
            = PendingIntent.getBroadcast(
                ctx,
                0,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            );

        alarmManager.set(AlarmManager.RTC_WAKEUP, notifyDelayInMillis, pendingIntent);
    }
}
