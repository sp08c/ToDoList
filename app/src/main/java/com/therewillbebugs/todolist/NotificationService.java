package com.therewillbebugs.todolist;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

import java.util.Calendar;

public class NotificationService {
    public NotificationManager manager;
    private NotificationCompat.Builder builder;
    private Context ctx;

    public NotificationService(Context ctx) {
        this.ctx = ctx;
        builder = new NotificationCompat.Builder(ctx);
        manager = (NotificationManager)ctx.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public Notification createNotification(Task t, Calendar notifyDateTime) {
        Notification n = builder.setSmallIcon(R.drawable.ic_add_black)
            .setContentTitle(ctx.getString(R.string.notification_title))
            .setContentText(t.getDescription() + " at " + t.getDateTimeString())
            .build();

        n.flags |= Notification.FLAG_AUTO_CANCEL;

        return n;
    }
}
