package com.cn.datalibrary.model

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import com.cn.datalibrary.api.ApiRepository
import com.ijcsj.common_library.bean.HistoryBase
import com.ijcsj.common_library.bean.HistoryBaseDatabase
import com.ijcsj.common_library.model.BaseModel
import com.ijcsj.common_library.util.DateUtil
import com.ijcsj.ui_library.utils.AppGlobals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.Date

class HistoryVideoModel  (private val repository: ApiRepository) : BaseModel() {

    fun d(){
        // 获取数据库实例

    }
    suspend  fun initData(currentPage:Int, startTime: Date,  endTime:Date): ObservableList<HistoryBase> {
        return   withContext(Dispatchers.IO) {
            val historyBaseList: ObservableList<HistoryBase> = ObservableArrayList()
            var list= AppGlobals.get()?.let {
                HistoryBaseDatabase.getDatabase(it).historyBaseDao().getMessagesBetweenTime(
                    startTime,endTime ,10, (currentPage - 1) * 10)
            }
            if (list != null) {
                historyBaseList.addAll(list)
            }
            historyBaseList
        }
    }

    suspend  fun getDataTotal(startTime: Date,  endTime:Date): Int {
        return   withContext(Dispatchers.IO) {
           var total:Int=0;
            var list= AppGlobals.get()?.let { HistoryBaseDatabase.getDatabase(it).historyBaseDao().getAllTimeData(startTime,endTime ) }
            if (list != null) {
                total=list.size/10;
                if (list.size%10!=0){
                    total++;
                }
            }
            total
        }
    }

    fun initDataBg(): ObservableList<HistoryBase> {
        val historyBaseList: ObservableList<HistoryBase> = ObservableArrayList()
        historyBaseList.add( HistoryBase("1","故障原因","2024-12-12  12:23:34","已处理"))
        historyBaseList.add(HistoryBase("2","故障原因","2024-12-12  12:23:34","已处理"))
        historyBaseList.add(HistoryBase("3","故障原因","2024-12-12  12:23:34","已处理"))
        historyBaseList.add(HistoryBase("4","故障原因","2024-12-12  12:23:34","已处理"))
        historyBaseList.add(HistoryBase("5","故障原因","2024-12-12  12:23:34","已处理"))
        historyBaseList.add(HistoryBase("6","故障原因","2024-12-12  12:23:34","已处理"))
        historyBaseList.add(HistoryBase("7","故障原因","2024-12-12  12:23:34","已处理"))
        historyBaseList.add(HistoryBase("8","故障原因","2024-12-12  12:23:34","已处理"))
        historyBaseList.add(HistoryBase("9","故障原因","2024-12-12  12:23:34","已处理"))
        historyBaseList.add(HistoryBase("10","故障原因","2024-12-12  12:23:34","已处理"))
        return historyBaseList
    }
}