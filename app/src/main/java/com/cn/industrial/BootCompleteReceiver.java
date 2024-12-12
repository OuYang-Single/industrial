package com.cn.industrial;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;



public class BootCompleteReceiver extends BroadcastReceiver {
    private static final String TAG = "BootCompleteReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
                Intent powerServiceIntent = new Intent(context, PowerService.class);
                context.startForegroundService(powerServiceIntent);
                Log.d(TAG, "startForegroundService");
            }
        }
    }
}