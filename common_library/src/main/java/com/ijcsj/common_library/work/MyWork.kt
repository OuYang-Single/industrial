package com.ijcsj.common_library.work

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.ijcsj.common_library.util.Constant
import com.ijcsj.common_library.util.DbHolders
import com.yaoxiaowen.download.DownloadHelper
import com.yaoxiaowen.download.DownloadStatus.LOADING
import com.yaoxiaowen.download.DownloadStatus.PAUSE
import com.yaoxiaowen.download.FileInfo
import java.io.File
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors

class MyWork(context: Context, workerParams: WorkerParameters) : Worker(context,workerParams) {
    companion object {
        @SuppressLint("InvalidPeriodicWorkRequestInterval")
        fun enqueue(context: Context) {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED)
                .build()
      /*  var     workRequest :PeriodicWorkRequest  =  PeriodicWorkRequest.Builder(MyWork.class, 1, TimeUnit.MINUTES)
                .setConstraints(constraints) // 设置约束条件
                .build();*/
           /* val request = OneTimeWorkRequestBuilder<MyWork>()
                //-----1-----添加约束
                .setConstraints(constraints)
                //-----2----- 传入执行worker需要的数据
                .setInputData(Data.Builder().putString("parameter1", "value of parameter1").build())
                //-----3-----设置避退策略
                .setBackoffCriteria(BackoffPolicy.EXPONENTIAL, 1, TimeUnit.MINUTES)
                .build()*/
           var workRequest: PeriodicWorkRequest  =  PeriodicWorkRequest.Builder(
               MyWork::class.java,     1000 * 60 *20,
               TimeUnit.MICROSECONDS )
                .setConstraints(constraints) // 设置约束条件
                .build();

            //-----4-----将任务添加到队列中
           WorkManager.getInstance(context).enqueue(workRequest)
            //或者采用uniqueName执行
          //  WorkManager.getInstance(context).beginUniqueWork("uniqueName", ExistingWorkPolicy.REPLACE, request).enqueue()
            //-----5-----对任务加入监听
        /*    WorkManager.getInstance(context).getWorkInfoByIdLiveData(request.id).observe(context, Observer {
                //-----8----获取doWork中传入的参数

            })
            //或者采用tag的方式监听状态
            WorkManager.getInstance(context).getWorkInfosByTagLiveData("tagCountWorker").observe(context, Observer {

            })
            //或者采用uniqueName的形式监听任务执行的状态
            WorkManager.getInstance(context).getWorkInfosForUniqueWorkLiveData("uniqueName").observe(context, Observer {

            })*/
        }
    }
    override fun doWork(): Result {
        Log.w("MyWork","任务执行")
        val datums: MutableList<FileInfo> =  DbHolders(applicationContext).getFileInfo()
        var  queryDatum =  datums.stream()
            .filter { person -> (PAUSE==person. downloadStatus)||(LOADING==person. downloadStatus)}
            .collect(Collectors.toList())
        for (i in queryDatum.indices) {
            DownloadHelper.getInstance().addTask(queryDatum[i].downloadUrl,File(queryDatum[i].filePath), Constant.FIRST_BC_ACTION).submit(applicationContext)
        }
        return Result.failure();
    }
}