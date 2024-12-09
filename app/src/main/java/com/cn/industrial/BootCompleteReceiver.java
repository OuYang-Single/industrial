package com.cn.industrial;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.ijcsj.common_library.can.Socketcan;

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
      //  Toast.makeText(context, "开机完成！"+ Socketcan.fd, Toast.LENGTH_SHORT).show();
    }
}