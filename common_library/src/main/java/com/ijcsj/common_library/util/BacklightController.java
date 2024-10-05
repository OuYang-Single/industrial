package com.ijcsj.common_library.util;

import android.content.ContentResolver;
import android.provider.Settings;
import android.util.Log;
 
public class BacklightController {
 
    private static final String TAG = "BacklightController";
    private static final int DEFAULT_SCREEN_BRIGHTNESS = 30000; // 默认背光亮度值
 
    // 设置背光时间（以毫秒为单位）
    public static void setScreenOffTimeout(ContentResolver resolver, int timeout) {

        Settings.System.putInt(resolver, Settings.System.SCREEN_OFF_TIMEOUT, timeout);
    }
 
    // 获取背光时间（以毫秒为单位）
    public static int getScreenOffTimeout(ContentResolver resolver) {
        return Settings.System.getInt(resolver, Settings.System.SCREEN_OFF_TIMEOUT, DEFAULT_SCREEN_BRIGHTNESS);
    }
}