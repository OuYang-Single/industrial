package com.cn.industrial;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ijcsj.common_library.mmkv.ShuJuMMkV;
import com.ijcsj.common_library.util.a;


public class BootCompleteReceiver extends BroadcastReceiver {
    private static final String TAG = "BootCompleteReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
           /* ShuJuMMkV.Companion.getInstance().putString(a.WORKING_MODE,"5");
            ShuJuMMkV.Companion.getInstance().putString(a.INLETVALVE_1,"0");
            ShuJuMMkV.Companion.getInstance().putString(a.INLETVALVE_2,"0");
            ShuJuMMkV.Companion.getInstance().putString(a.INLETVALVE_3,"0");
            ShuJuMMkV.Companion.getInstance().putString(a.INLETVALVE_4,"0");
            ShuJuMMkV.Companion.getInstance().putString(a.INLETVALVE_5,"0");
            ShuJuMMkV.Companion.getInstance().putString(a.INLETVALVE_6,"0");
            ShuJuMMkV.Companion.getInstance().putString(a.INLETVALVE_7,"0");*/
        }catch (Exception e){

        }
        if (intent != null) {

            if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
                Intent powerServiceIntent = new Intent(context, PowerService.class);
                context.startForegroundService(powerServiceIntent);
                Log.d(TAG, "startForegroundService");

            }
        }
    }
}