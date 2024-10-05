package com.ijcsj.ui_library.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ijcsj.ui_library.R;


/**
 * 单例Toast工具类
 * <p>
 * 1.解决toast排队的问题
 * 2.修复Toast在android 7.1手机上的BadTokenException
 * 3.兼容位置、时长、stringId
 */
public class ToastUtils {
  private static   Toast customToast;
    /**
     * show Toast 默认短时长 {@link Toast#LENGTH_SHORT}
     *
     * @param context Context
     * @param message 内容
     */
    public static void show(Context context, String message) {
        show(context, message, Toast.LENGTH_SHORT);
    }

    /**
     * show Toast 可选时长
     *
     * @param context  Context
     * @param message  内容
     * @param duration {@link Toast#LENGTH_SHORT},{@link Toast#LENGTH_LONG}
     */
    public static void show(Context context, String message, int duration) {
        Toast.makeText(context, message, duration).show();
    }

    public static Toast makeText(Context context, CharSequence text) {

        if (customToast == null){
            customToast  = new Toast(context);
        }else {
            customToast.cancel();
            customToast =  new Toast(context);
        }
        //获得view的布局
        View customView = LayoutInflater.from(context).inflate(R.layout.transient_notification,null);
        TextView tv = (TextView)customView.findViewById(R.id.message);
        tv.setText(text);
        customToast.setView(customView);
        customToast.setDuration(Toast.LENGTH_SHORT);
        customToast.setGravity(Gravity.BOTTOM,0,70);
        customToast.show();
        return customToast;
    }
}


