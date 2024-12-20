package com.ijcsj.inlet_library.servicel

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_CAMERA
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_DATA_SYNC
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PLAYBACK
import android.content.pm.ServiceInfo.FOREGROUND_SERVICE_TYPE_MEDIA_PROJECTION
import android.net.wifi.WifiManager
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import com.allenliu.versionchecklib.utils.ALog
import com.allenliu.versionchecklib.v2.builder.BuilderManager
import com.allenliu.versionchecklib.v2.ui.NotificationHelper
import com.ijcsj.common_library.can.Socketcan
import com.ijcsj.common_library.mmkv.ShuJuMMkV
import com.ijcsj.common_library.util.ActivityManager
import com.ijcsj.common_library.util.Constant
import com.ijcsj.common_library.util.DbHolders
import com.ijcsj.common_library.util.LiveDataBus
import com.ijcsj.common_library.util.NetworkStrengthUtil
import com.ijcsj.common_library.util.a
import com.ijcsj.common_library.work.MyWork
import com.ijcsj.ui_library.utils.AppGlobals
import com.orhanobut.logger.Logger
import com.yaoxiaowen.download.DownloadConstant
import com.yaoxiaowen.download.DownloadHelper
import com.yaoxiaowen.download.DownloadStatus
import com.yaoxiaowen.download.FileInfo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors


class VersionsServices : Service() {
    private var notificationHelper: NotificationHelper? = null
    private var isServiceAlive = false
    private var executors: ExecutorService? = null
    var  d: Disposable?=null
    var socketcan: Socketcan?=null;
    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
      /*  if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }*/
        socketcan= Socketcan();
        Log.w("VersionsServices","Application  -----")
        Socketcan.fd=Socketcan.OpenCan("can0")
        socketcan?.timeLoop(  AppGlobals.get())
        LiveDataBus.get().with("Socketcan_Data", Socketcan::class.java ).postValue(socketcan)
        CAN_105()
        CAN_106()
        CAN_107()
        CAN_108()
        Logger.w("App onCreate ${ Socketcan.fd}");
        ALog.e("version service create")
        Log.w("VersionsServices","onStartCommand create")
        init()
        Observable.interval(0, 1000, TimeUnit.MILLISECONDS)
            .map<Long> { mTimer: Long -> mTimer + 1 }
            .subscribeOn(Schedulers.io())
            .map {
                Log.w("VersionsServices","onStartCommand Observable")
                if ( ActivityManager.getInstance().activityList.size<=0){
                    Log.w("VersionsServices","onStartCommand getInstance---")
                    Socketcan.CloseCan(Socketcan.fd)
                    d?.dispose()
                    if (Socketcan.d!=null){
                        Socketcan.d.dispose()
                    }
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Unit> {
                override fun onSubscribe(d: Disposable) {
                   this@VersionsServices.d=d
                }

                override fun onError(e: Throwable) {

                }

                override fun onComplete() {

                }

                override fun onNext(t: Unit) {

                }


            });
        val filter = IntentFilter()
        filter.addAction(Constant.FIRST_UPDATE_ACTION)
        filter.addAction(Constant.FIRST_BC_ACTION)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            registerReceiver(receiver, filter, RECEIVER_NOT_EXPORTED)
        } else {
            registerReceiver(receiver, filter)
        }
        return START_REDELIVER_INTENT
    }

    companion object {
        fun enqueueWork(context: Context) {
            //清除之前的任务，如果有
//            AllenVersionChecker.getInstance().cancelAllMission()
            val intent = Intent(context, VersionsServices::class.java)
            //显示通知栏的情况 才设置为前台服务
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context.startForegroundService(intent)
            } else {
                context.startService(intent)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ALog.e("version service destroy")
        Log.w("VersionsServices","onStartCommand destroy")
        BuilderManager.doWhenNotNull {
            if (isRunOnForegroundService) {
                stopForeground(true)
            }
        }
        unregisterReceiver(receiver)
    }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    private val receiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (null != intent) {
                when (intent.action) {
                    Constant.FIRST_BC_ACTION -> {
                        val fileInfo: FileInfo? = intent.getSerializableExtra(
                            DownloadConstant.EXTRA_INTENT_DOWNLOAD
                        ) as FileInfo?

                    }
                    else -> {

                    }
                }
            }
        }
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

    private fun init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "channel_id",
                "channel_name",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
            val notification: Notification = Notification. Builder(this@VersionsServices, "channel_id")
                .build()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                startForeground(232323, notification, FOREGROUND_SERVICE_TYPE_DATA_SYNC);
            } else {
                startForeground(232323, notification);
            }
        }
        Logger.w("MyWork LogStart");
           MyWork.enqueue(this@VersionsServices)


    }


}