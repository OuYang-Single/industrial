package com.cn.industrial;
 
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import com.ijcsj.common_library.util.a;

import com.ijcsj.common_library.mmkv.ShuJuMMkV;

public class ShutdownBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG = "ShutdownBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
       Log.w("ouyang","关机");
       try {
           ShuJuMMkV.Companion.getInstance().putString(a.WORKING_MODE,"5");
           ShuJuMMkV.Companion.getInstance().putString(a.INLETVALVE_1,"0");
           ShuJuMMkV.Companion.getInstance().putString(a.INLETVALVE_2,"0");
           ShuJuMMkV.Companion.getInstance().putString(a.INLETVALVE_3,"0");
           ShuJuMMkV.Companion.getInstance().putString(a.INLETVALVE_4,"0");
           ShuJuMMkV.Companion.getInstance().putString(a.INLETVALVE_5,"0");
           ShuJuMMkV.Companion.getInstance().putString(a.INLETVALVE_6,"0");
           ShuJuMMkV.Companion.getInstance().putString(a.INLETVALVE_7,"0");
       }catch (Exception e){

       }

    }
}