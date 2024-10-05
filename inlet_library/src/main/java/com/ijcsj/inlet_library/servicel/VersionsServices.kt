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
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import com.allenliu.versionchecklib.utils.ALog
import com.allenliu.versionchecklib.v2.builder.BuilderManager
import com.allenliu.versionchecklib.v2.ui.NotificationHelper
import com.ijcsj.common_library.util.Constant
import com.ijcsj.common_library.util.DbHolders
import com.ijcsj.common_library.work.MyWork
import com.orhanobut.logger.Logger
import com.yaoxiaowen.download.DownloadConstant
import com.yaoxiaowen.download.DownloadHelper
import com.yaoxiaowen.download.DownloadStatus
import com.yaoxiaowen.download.FileInfo
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.stream.Collectors


class VersionsServices : Service() {
    private var notificationHelper: NotificationHelper? = null
    private var isServiceAlive = false
    private var executors: ExecutorService? = null

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
      /*  if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }*/
        ALog.e("version service create")
        init()
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