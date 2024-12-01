package com.cn.setuplibrary.model

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import com.cn.setuplibrary.api.ApiRepository
import com.ijcsj.common_library.bean.DataBaseDatabase
import com.ijcsj.common_library.bean.DatasBase
import com.ijcsj.common_library.bean.HistoryBase
import com.ijcsj.common_library.bean.HistoryBaseDatabase
import com.ijcsj.common_library.model.BaseModel
import com.ijcsj.ui_library.utils.AppGlobals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Date

class OperationLogModel  (private val repository: ApiRepository) : BaseModel() {

    suspend  fun initData(currentPage:Int,int: Int,boolean: Boolean,canId:String ): ObservableList<DatasBase> {
        return   withContext(Dispatchers.IO) {
            val historyBaseList: ObservableList<DatasBase> = ObservableArrayList()
            var list= AppGlobals.get()?.let {
                 when(int){
                    1->{
                        AppGlobals.get()?.let { DataBaseDatabase.getDatabase(it).backFlowBaseDao().getTypes(boolean, 9, (currentPage - 1) * 9) }
                    }
                    2->{
                        AppGlobals.get()?.let { DataBaseDatabase.getDatabase(it).backFlowBaseDao().getCanIds(canId,9, (currentPage - 1) * 9 ) }
                    }
                    3->{
                        AppGlobals.get()?.let { DataBaseDatabase.getDatabase(it).backFlowBaseDao().getCanIdAndTypes(canId,boolean ,9, (currentPage - 1) * 9) }
                    }
                    else->{
                        DataBaseDatabase.getDatabase(it).backFlowBaseDao().getMessagesBetweenTime(
                            9, (currentPage - 1) * 9)
                    }
                }

            }
            if (list != null) {
                historyBaseList.addAll(list)
            }
            historyBaseList
        }
    }

    suspend  fun getDataTotal( int: Int,boolean: Boolean,canId:String): Int {
        return   withContext(Dispatchers.IO) {
            var total:Int=0;
            var list= when(int){
             1->{  AppGlobals.get()?.let { DataBaseDatabase.getDatabase(it).backFlowBaseDao().getType(boolean ) }}
             2->{  AppGlobals.get()?.let { DataBaseDatabase.getDatabase(it).backFlowBaseDao().getCanId(canId ) }}
             3->{  AppGlobals.get()?.let { DataBaseDatabase.getDatabase(it).backFlowBaseDao().getCanIdAndType(canId,boolean ) }}
             else->{
                AppGlobals.get()?.let { DataBaseDatabase.getDatabase(it).backFlowBaseDao().getAllData( ) }
             }
            }

            if (list != null) {
                total=list.size/9;
                if (list.size%9!=0){
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
        return historyBaseList
    }
}