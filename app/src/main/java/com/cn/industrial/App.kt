package com.cn.industrial

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import com.ijcsj.common_library.bean.HistoryBase
import com.ijcsj.common_library.bean.HistoryBaseDatabase
import com.ijcsj.common_library.bean.TemperatureBase
import com.ijcsj.common_library.bean.TemperatureBaseDatabase
import com.ijcsj.common_library.can.Socketcan
import com.ijcsj.common_library.util.ActivityManager
import com.ijcsj.common_library.util.DateUtil
import com.ijcsj.common_library.util.LiveDataBus
import com.orhanobut.logger.Logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Calendar


class App : Application() {
    var activityc: Activity?=null;
   var socketcan: Socketcan?=null;
    override fun onCreate() {
        super.onCreate()
        instance = this
        socketcan= Socketcan();
        Socketcan.fd=Socketcan.OpenCan("can0")
        LiveDataBus.get().with("Socketcan_Data", Socketcan::class.java ).postValue(socketcan)
        socketcan?.timeLoop()

        registerActivityLifecycleCallbacks(object :ActivityLifecycleCallbacks{
            override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                Logger.w("App $activity");
                ActivityManager.getInstance().addActivity(activity)
            }

            override fun onActivityStarted(activity: Activity) {

            }

            override fun onActivityResumed(activity: Activity) {

            }

            override fun onActivityPaused(activity: Activity) {

            }

            override fun onActivityStopped(activity: Activity) {

            }

            override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {

            }

            override fun onActivityDestroyed(activity: Activity) {
                ActivityManager.getInstance().removeActivity(activity)
                if ( ActivityManager.getInstance().activityList.size<=0){
                    Socketcan.CloseCan(Socketcan.fd)
                }
            }

        });

    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        var instance: App? = null
            private set
    }

    fun isAdopt(context: Context): Boolean {
        val intentFilter = IntentFilter(
            Intent.ACTION_BATTERY_CHANGED
        )
        val batteryStatusIntent = context.registerReceiver(null, intentFilter)
        val voltage = batteryStatusIntent!!.getIntExtra("voltage", 99999)
        val temperature = batteryStatusIntent.getIntExtra("temperature", 99999)
        return if (voltage == 0 && temperature == 0 || voltage == 10000 && temperature == 0) {
            //这是通过电池的伏数和温度来判断是真机还是模拟器
            true
        } else {
            false
        }
    }

}