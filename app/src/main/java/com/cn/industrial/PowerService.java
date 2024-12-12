package com.cn.industrial;
 
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
 
import androidx.annotation.Nullable;
 
public class PowerService extends Service {
 
    private static final String TAG = "PowerService";
    public ShutdownBroadcastReceiver mShutdownBroadcastReceiver;
 
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        mShutdownBroadcastReceiver = new ShutdownBroadcastReceiver();
    }
 
    private Notification getNotification() {
        NotificationChannel channel = new NotificationChannel("channel_id", "channel_name", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager != null) {
            manager.createNotificationChannel(channel);
        }
        return new Notification.Builder(this, "channel_id")
                .setContentTitle("shutdown")
                .setContentText("Listening for shutdown")
//                .setAutoCancel(true)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .build();
    }
 
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        startForeground(1, getNotification());
        registerBroadcast();
        return START_STICKY;
    }
 
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        unregisterBroadcast();
        stopForeground(true);
        stopSelf();
    }
 
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
 
    public void registerBroadcast() {
        Log.d(TAG, "registerBroadcast");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction((Intent.ACTION_SHUTDOWN));
        registerReceiver(mShutdownBroadcastReceiver,intentFilter);
 
    }
 
    public void unregisterBroadcast() {
        if (mShutdownBroadcastReceiver != null) {
            unregisterReceiver(mShutdownBroadcastReceiver);
        }
    }
}