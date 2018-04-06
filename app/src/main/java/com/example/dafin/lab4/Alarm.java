package com.example.dafin.lab4;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import static android.content.Context.NOTIFICATION_SERVICE;


public class Alarm extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "");
        wl.acquire();

        int lastItems = MainActivity.lastItems;

        if (MainActivity.adapter.getItemCount() > lastItems) {

            Notification.Builder builder = new Notification.Builder(context);
            builder.setContentTitle("Scheduled Notification");
            builder.setContentText("New Messages!");
            builder.setSmallIcon(R.drawable.googleg_standard_color_18);


            String mNotificationId = String.valueOf(System.currentTimeMillis()); //This creates random notifications id

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            Notification notification = builder.build();
            int id = intent.getIntExtra(mNotificationId, 0);
            notificationManager.notify(id, notification);

        }
        wl.release();

    }

    public void setAlarm(Context context)
    {
        AlarmManager am =( AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent("com.example.wolfer.lab4redo.START_ALARM");
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, i, 0);

        //Checks for update every  minute

        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000 * 60 * 1, pi); // Millisec * Second * Minute
    }

    public void cancelAlarm(Context context)
    {
        Intent intent = new Intent(context, Alarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
}