package com.example.dafin.lab4;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class ReceiveMessageService extends Service {
    public ReceiveMessageService() {
    }

    Alarm alarm = new Alarm();
    public void onCreate()
    {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        alarm.setAlarm(this);
        return START_STICKY;
    }

    @Override
    public void onStart(Intent intent, int startId)
    {
        alarm.setAlarm(this);
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
}