package com.example.admin.something;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;


public class MyService extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(1, new Notification());

        IntentFilter filter = new IntentFilter();

        filter.addAction(Intent.ACTION_BOOT_COMPLETED);
        filter.addAction(Intent.ACTION_SCREEN_OFF);

        registerReceiver(new MyReceiver(), filter);
        return START_REDELIVER_INTENT;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
