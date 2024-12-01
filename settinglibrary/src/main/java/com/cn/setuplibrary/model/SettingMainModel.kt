package com.cn.setuplibrary.model

import android.graphics.Color
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.fragment.app.Fragment
import com.ijcsj.common_library.model.BaseModel
import com.cn.setuplibrary.api.ApiRepository
import com.cn.setuplibrary.fragment.SettingFactoryFragment
import com.cn.setuplibrary.fragment.SettingProjectFragment
import com.cn.setuplibrary.fragment.SettingUserFragment
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.ijcsj.common_library.bean.BackFlowBase
import com.ijcsj.common_library.bean.BackFlowBaseDatabase
import com.ijcsj.common_library.bean.DataBaseDatabase
import com.ijcsj.common_library.bean.DataTitle
import com.ijcsj.common_library.bean.DatasBase
import com.ijcsj.common_library.bean.HistoryBase
import com.ijcsj.common_library.bean.HistoryBaseDatabase
import com.ijcsj.common_library.bean.SetUpBaseDatabase
import com.ijcsj.common_library.bean.SetUpBean
import com.ijcsj.common_library.bean.TemperatureBase
import com.ijcsj.common_library.bean.TemperatureBaseDatabase
import com.ijcsj.common_library.util.DateUtil
import com.ijcsj.ui_library.utils.AppGlobals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Date
import java.util.stream.Collectors

class SettingMainModel(private val repository: ApiRepository) : BaseModel() {

    fun initData(): ObservableList<DataTitle> {
        val projectBaseList: ObservableList<DataTitle> = ObservableArrayList()
        var projectBase1= DataTitle("用户",true)
        var projectBase2= DataTitle("工程账号",false)
        var projectBase3= DataTitle("厂家账号",false)
        projectBaseList.add(projectBase1)
        projectBaseList.add(projectBase2)
        projectBaseList.add(projectBase3)
        return projectBaseList
    }
    fun initDataFragment(): ArrayList<Fragment> {
        val projectBaseList: ArrayList<Fragment> = ArrayList()
        projectBaseList.add(SettingUserFragment())
        projectBaseList.add(SettingProjectFragment())
        projectBaseList.add(SettingFactoryFragment())
        return projectBaseList
    }
    suspend  fun initDatas(startTime: Date, endTime: Date): ObservableList<LineDataSet> {
        return   withContext(Dispatchers.IO) {
            val lineDataSetList: ObservableList<LineDataSet> = ObservableArrayList()
            val historyBaseList: ObservableList<TemperatureBase> = ObservableArrayList()
            val historyBaseLists: ObservableList<Entry> = ObservableArrayList()
            var listT= AppGlobals.get()?.let {
                TemperatureBaseDatabase.getDatabase(it).temperatureBaseDao().getMessagesBetweenTime(startTime,endTime)
            }
            var i = 0
            if (listT != null) {
                historyBaseList.addAll(listT)
            }
            val userDTOs = historyBaseList.stream()
                .map<Entry> { user: TemperatureBase ->
                    i++
                    Entry(i.toFloat(), user.temperature.toFloat(), user)
                }.collect(Collectors.toList<Entry>())
            if (userDTOs!=null){
                historyBaseLists.addAll(userDTOs)
            }
            val lineDataSet = LineDataSet(historyBaseLists, "出水温度")
            // 不显示坐标点的小圆点
            lineDataSet.setDrawCircles(false);
            // 不显示坐标点的数据
            lineDataSet.setDrawValues(false);
            // 不显示定位线
            lineDataSet.isHighlightEnabled = false;
            lineDataSet.setColor(Color.parseColor("#0048FF"));
            /* lineDataSet.setValueTextColor(Color.parseColor("#0048FF"));*/
            lineDataSet.setValueTextColor(Color.RED); // 设置为红色
            lineDataSet.mode = LineDataSet.Mode.CUBIC_BEZIER;
            lineDataSet.setLineWidth(0.5f);
            lineDataSet.setCircleColor(Color.TRANSPARENT);
            lineDataSet.circleRadius = 0.1f
            lineDataSet.valueTextColor= Color.parseColor("#0048FF")
            lineDataSetList.add(lineDataSet)


            val backFlowEntryLists: ObservableList<Entry> = ObservableArrayList()
            var listD= AppGlobals.get()?.let {
                BackFlowBaseDatabase.getDatabase(it).backFlowBaseDao().getMessagesBetweenTime(startTime,endTime)
            }
            val backFlowBaseList: ObservableList<BackFlowBase> = ObservableArrayList()
            var a = 0
            if (listD != null) {
                backFlowBaseList.addAll(listD)
            }
            val userDTOa = backFlowBaseList.stream()
                .map<Entry> { user: BackFlowBase ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat(), user)
                }.collect(Collectors.toList<Entry>())
            if (userDTOa!=null){
                backFlowEntryLists.addAll(userDTOa)
            }
            val lineDataSet1 = LineDataSet(backFlowEntryLists, "回水温度")
            // 不显示坐标点的小圆点
            lineDataSet1.setDrawCircles(false);
            // 不显示坐标点的数据
            lineDataSet1.setDrawValues(false);
            // 不显示定位线
            lineDataSet1.isHighlightEnabled = false;
            lineDataSet1.setColor(Color.parseColor("#FFEB3B"));
            lineDataSet1.setValueTextColor(Color.parseColor("#FFEB3B"));
            lineDataSet1.setLineWidth(0.5f);
            lineDataSet1.mode = LineDataSet.Mode.CUBIC_BEZIER;
            lineDataSet1.setCircleColor(Color.TRANSPARENT);
            lineDataSet1.circleRadius = 0.1f
            lineDataSetList.add(lineDataSet1)



            val setUpBeanEntryLists: ObservableList<Entry> = ObservableArrayList()
            var listB= AppGlobals.get()?.let {
                SetUpBaseDatabase.getDatabase(it).backFlowBaseDao().getMessagesBetweenTime(startTime,endTime)
            }
            val setUpBaseList: ObservableList<SetUpBean> = ObservableArrayList()
            var b = 0
            if (listB != null) {
                setUpBaseList.addAll(listB)
            }
            val userDTOb = setUpBaseList.stream()
                .map<Entry> { user: SetUpBean ->
                    b++
                    Entry(b.toFloat(), user.temperature.toFloat()/10.toFloat(), user)
                }.collect(Collectors.toList<Entry>())
            if (userDTOa!=null){
                setUpBeanEntryLists.addAll(userDTOb)
            }
            val lineDataSet2 = LineDataSet(setUpBeanEntryLists, "设定温度")
            // 不显示坐标点的小圆点
            lineDataSet2.setDrawCircles(false);
            // 不显示坐标点的数据
            lineDataSet2.setDrawValues(false);
            // 不显示定位线
            lineDataSet2.isHighlightEnabled = false;
            lineDataSet2.setColor(Color.parseColor("#F44336"));

            lineDataSet2.setValueTextColor(Color.parseColor("#F44336"));
            lineDataSet2.setLineWidth(0.5f);
            lineDataSet2.setCircleColor(Color.TRANSPARENT);
            lineDataSet2.circleRadius = 0.1f
            lineDataSet2.mode = LineDataSet.Mode.CUBIC_BEZIER;
            lineDataSetList.add(lineDataSet2)
            lineDataSetList
        }
    }
    suspend  fun initDatass(): ObservableList<DatasBase> {
        return   withContext(Dispatchers.IO) {
            val historyBaseList: ObservableList<DatasBase> = ObservableArrayList()
            var list= AppGlobals.get()?.let {
                DataBaseDatabase.getDatabase(it).backFlowBaseDao().getAllData()
            }
            if (list != null) {
                historyBaseList.addAll(list)
            }
            historyBaseList
        }
    }
    suspend  fun dataDeletes() {
        withContext(Dispatchers.IO) {
            AppGlobals.get()?.let {
                HistoryBaseDatabase.getDatabase(it).historyBaseDao().delete(HistoryBaseDatabase.getDatabase(it).historyBaseDao().getAllData())
            }
        }

    }
}