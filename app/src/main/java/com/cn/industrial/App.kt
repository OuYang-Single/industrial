package com.cn.industrial

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import com.ijcsj.common_library.can.Socketcan
import com.ijcsj.common_library.mmkv.ShuJuMMkV
import com.ijcsj.common_library.util.ActivityManager
import com.ijcsj.common_library.util.LiveDataBus
import com.ijcsj.common_library.util.a
import com.orhanobut.logger.Logger
import com.tencent.bugly.crashreport.CrashReport


class App : Application() {
    var activityc: Activity?=null;
   var socketcan: Socketcan?=null;
    override fun onCreate() {
        super.onCreate()
        CrashReport.initCrashReport(this, "123a7f1ca3", false);
        instance = this
        socketcan= Socketcan();
        Socketcan.fd=Socketcan.OpenCan("can0")
        LiveDataBus.get().with("Socketcan_Data", Socketcan::class.java ).postValue(socketcan)
        ShuJuMMkV.getInstances()?.putBoolean(a.USER_LOG_ON,false)
        ShuJuMMkV.getInstances()?.putBoolean(a.ENGINEERING_LOG_ON,false)
        ShuJuMMkV.getInstances()?.putBoolean(a.MANUFACTOR_LOG_ON,false)
        ShuJuMMkV.instance?.putString(a.INLETVALVE_1, "0")
        ShuJuMMkV.instance?.putString(a.INLETVALVE_2, "0")
        ShuJuMMkV.instance?.putString(a.INLETVALVE_3, "0")
        ShuJuMMkV.instance?.putString(a.INLETVALVE_4, "0")
        ShuJuMMkV.instance?.putString(a.INLETVALVE_5, "0")
        ShuJuMMkV.instance?.putString(a.INLETVALVE_6, "0")
        ShuJuMMkV.instance?.putString(a.INLETVALVE_7, "0")
        socketcan?.timeLoop(this)
        CAN_105()
        CAN_106()
        CAN_107()
        CAN_108()
        Logger.w("App onCreate ${ Socketcan.fd}");
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
    fun CAN_105():Boolean{
        var bytes2=ByteArray(8)
        var d0= ShuJuMMkV.getInstances()?.getString(a.TEMP_MODE,"0")
        var d1=  ShuJuMMkV.getInstances()?.getString(a.TEMPERATURE_DIFFERENCE,"50")
        var d2=  ShuJuMMkV.getInstances()?.getString(a.HIGH_DEVIATION,"50")
        var d3=  ShuJuMMkV.getInstances()?.getString(a.LOW_DEVIATION,"50")
        var d4= ShuJuMMkV.getInstances()?.getString(a.TEMPERATURE_DEVIATION_TIME,"20")
        var d5=  ShuJuMMkV.getInstances()?.getString(a.HEATING_TIMEOUT,"120")
        var d6=  ShuJuMMkV.getInstances()?.getString(a.COOLING_TIMEOUT,"120")
        bytes2[0]=(d0!!.toInt()and 0xff).toByte()
        bytes2[1]=(d1!!.toInt()and 0xff).toByte()
        bytes2[2]=(d2!!.toInt()and 0xff).toByte()
        bytes2[3]=(d3!!.toInt()and 0xff).toByte()
        bytes2[4]= (d4!!.toInt()and 0xff).toByte()
        bytes2[5]= (d5!!.toInt() and 0xff).toByte()
        bytes2[6]= (d6!!.toInt()and 0xff).toByte()
        var ta=  Socketcan.CanWrites(Socketcan.fd,Socketcan.CAN_105,bytes2)
        Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
        if (ta>0){
            return true;
        }else{
            return false;
        }
    }

    fun CAN_106():Boolean{
        var bytes2=ByteArray(8)
        var d0=   ShuJuMMkV.getInstances()?.getString(a.EXHAUST_PRESSURE,"50")
        var d1=   ShuJuMMkV.getInstances()?.getString(a.RETURN_PRESSURE_DIFFERENCE,"50")
        var d2=   ShuJuMMkV.getInstances()?.getString(a.HIGH_PRESSURE_DEVIATION,"130")
        var d3=    ShuJuMMkV.getInstances()?.getString(a.LOW_PRESSURE_DEVIATION,"20")
        var d4=    ShuJuMMkV.getInstances()?.getString(a.MINIMUM_INLET_PRESSURE,"10")
        var d5=    ShuJuMMkV.getInstances()?.getString(a.MAXIMUM_RETURN_WATER_PRESSURE,"25")
        var d6=   ShuJuMMkV.getInstances()?.getString(a.MINIMUM_PUMP_PRESSURE,"5")
        bytes2[0]=(d0!!.toInt()and 0xff).toByte()
        bytes2[1]=(d1!!.toInt()and 0xff).toByte()
        bytes2[2]=(d2!!.toInt()and 0xff).toByte()
        bytes2[3]=  (d3!!.toInt()and 0xff).toByte()
        bytes2[4]= (d4!!.toInt()and 0xff).toByte()
        bytes2[5]= (d5!!.toInt() and 0xff).toByte()
        bytes2[6]= (d6!!.toInt()and 0xff).toByte()

        var ta=  Socketcan.CanWrites(Socketcan.fd,Socketcan.CAN_106,bytes2)
        Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
        if (ta>0){
            return true
        }else{
            return false
        }
    }
    fun CAN_107():Boolean{
        var bytes2=ByteArray(8)
        var d1=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_11,"0")
        var d2=  ShuJuMMkV.getInstances()?.getString(a.INLETVALVE_12,"0")
        bytes2[0]=(d1!!.toInt()and 0xff).toByte()
        bytes2[1]=(d2!!.toInt()and 0xff).toByte()
        var ta=  Socketcan.CanWrites(Socketcan.fd,Socketcan.CAN_107,bytes2)
        Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
        if (ta>0){
            return true
        }else{
            return false
        }
    }
    fun CAN_108():Boolean{
        var bytes2=ByteArray(8)
        var d0=    ShuJuMMkV.getInstances()?.getString(a.PID_P,"100")
        var d1=   ShuJuMMkV.getInstances()?.getString(a.PID_I,"1")
        var d2=   ShuJuMMkV.getInstances()?.getString(a.PID_D,"10")
        var d3=   ShuJuMMkV.getInstances()?.getString(a.COAL_COMPENSATION,"0")
        var d4=      ShuJuMMkV.getInstances()?.getString(a.COAL_RETURN_COMPENSATION,"0")
        bytes2[0]= (d0!!.toInt() and 0xff).toByte()
        bytes2[1]= (d1!!.toInt() and 0xff).toByte()
        bytes2[2]= (d2!!.toInt() and 0xff).toByte()
        bytes2[3]= (d3!!.toInt() and 0xff).toByte()
        bytes2[4]= (d4!!.toInt() and 0xff).toByte()
        var a1=  Socketcan.CanWrites(Socketcan.fd, Socketcan.CAN_108,bytes2)
        if (a1>0){
            return true
        }else{
            return false
        }
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