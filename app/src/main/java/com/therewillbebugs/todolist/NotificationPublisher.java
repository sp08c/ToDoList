/*
  Modeled after code found at https://gist.github.com/BrandonSmith/6679223
 */

package com.therewillbebugs.todolist;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationPublisher extends BroadcastReceiver {
    public static String NOTIFICATION_ID;
    public static String NOTIFICATION;

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        int id = intent.getIntExtra(NOTIFICATION_ID, 0);
        Notification n = intent.getParcelableExtra(NOTIFICATION);

        manager.notify(id, n);
    }
}
