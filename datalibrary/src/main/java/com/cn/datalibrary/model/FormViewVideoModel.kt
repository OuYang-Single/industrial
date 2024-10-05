package com.cn.datalibrary.model

import android.graphics.Color
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import com.cn.datalibrary.R
import com.cn.datalibrary.api.ApiRepository
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.ijcsj.common_library.bean.BackFlowBase
import com.ijcsj.common_library.bean.BackFlowBaseDatabase
import com.ijcsj.common_library.bean.HistoryBaseDatabase
import com.ijcsj.common_library.bean.SetUpBaseDatabase
import com.ijcsj.common_library.bean.SetUpBean
import com.ijcsj.common_library.bean.TemperatureBase
import com.ijcsj.common_library.bean.TemperatureBaseDatabase
import com.ijcsj.common_library.model.BaseModel
import com.ijcsj.ui_library.utils.AppGlobals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Date
import java.util.stream.Collectors

class FormViewVideoModel  (private val repository: ApiRepository) : BaseModel() {


    suspend  fun initData(startTime: Date, endTime: Date): ObservableList<LineDataSet> {
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
            lineDataSet.valueTextColor=Color.parseColor("#0048FF")
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
    /*        val userDTOa1 = backFlowBaseList.stream()
                .map<Entry> { user: BackFlowBase ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat(), user)
                }.collect(Collectors.toList<Entry>())
            val userDTOa2 = backFlowBaseList.stream()
                .map<Entry> { user: BackFlowBase ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat(), user)
                }.collect(Collectors.toList<Entry>())
            val userDTOa3 = backFlowBaseList.stream()
                .map<Entry> { user: BackFlowBase ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat(), user)
                }.collect(Collectors.toList<Entry>())
            val userDTOa4 = backFlowBaseList.stream()
                .map<Entry> { user: BackFlowBase ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat(), user)
                }.collect(Collectors.toList<Entry>())
            val userDTOa5 = backFlowBaseList.stream()
                .map<Entry> { user: BackFlowBase ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat(), user)
                }.collect(Collectors.toList<Entry>())
            val userDTOa6 = backFlowBaseList.stream()
                .map<Entry> { user: BackFlowBase ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat(), user)
                }.collect(Collectors.toList<Entry>())
            val userDTOa7 = backFlowBaseList.stream()
                .map<Entry> { user: BackFlowBase ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat(), user)
                }.collect(Collectors.toList<Entry>())
            val userDTOa8 = backFlowBaseList.stream()
                .map<Entry> { user: BackFlowBase ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat(), user)
                }.collect(Collectors.toList<Entry>())
            val userDTOa9 = backFlowBaseList.stream()
                .map<Entry> { user: BackFlowBase ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat(), user)
                }.collect(Collectors.toList<Entry>())
            val userDTOa10 = backFlowBaseList.stream()
                .map<Entry> { user: BackFlowBase ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat(), user)
                }.collect(Collectors.toList<Entry>())
            val userDTOa11 = backFlowBaseList.stream()
                .map<Entry> { user: BackFlowBase ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat(), user)
                }.collect(Collectors.toList<Entry>())
            val userDTOa12 = backFlowBaseList.stream()
                .map<Entry> { user: BackFlowBase ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat(), user)
                }.collect(Collectors.toList<Entry>())
            val userDTOa13 = backFlowBaseList.stream()
                .map<Entry> { user: BackFlowBase ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat(), user)
                }.collect(Collectors.toList<Entry>())
            val userDTOa14 = backFlowBaseList.stream()
                .map<Entry> { user: BackFlowBase ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat(), user)
                }.collect(Collectors.toList<Entry>())
            val userDTOa15 = backFlowBaseList.stream()
                .map<Entry> { user: BackFlowBase ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat(), user)
                }.collect(Collectors.toList<Entry>())
            val userDTOa16 = backFlowBaseList.stream()
                .map<Entry> { user: BackFlowBase ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat(), user)
                }.collect(Collectors.toList<Entry>())
            val userDTOa17 = backFlowBaseList.stream()
                .map<Entry> { user: BackFlowBase ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat(), user)
                }.collect(Collectors.toList<Entry>())
            val userDTOa18 = backFlowBaseList.stream()
                .map<Entry> { user: BackFlowBase ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat(), user)
                }.collect(Collectors.toList<Entry>())
            val userDTOa19= backFlowBaseList.stream()
                .map<Entry> { user: BackFlowBase ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat(), user)
                }.collect(Collectors.toList<Entry>())
            val userDTOa20= backFlowBaseList.stream()
                .map<Entry> { user: BackFlowBase ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat(), user)
                }.collect(Collectors.toList<Entry>())*/
            if (userDTOa!=null){
                backFlowEntryLists.addAll(userDTOa)
       /*         backFlowEntryLists.addAll(userDTOa1)
                backFlowEntryLists.addAll(userDTOa2)
                backFlowEntryLists.addAll(userDTOa3)
                backFlowEntryLists.addAll(userDTOa4)
                backFlowEntryLists.addAll(userDTOa5)
                backFlowEntryLists.addAll(userDTOa6)
                backFlowEntryLists.addAll(userDTOa7)
                backFlowEntryLists.addAll(userDTOa8)
                backFlowEntryLists.addAll(userDTOa9)
                backFlowEntryLists.addAll(userDTOa10)
                backFlowEntryLists.addAll(userDTOa11)
                backFlowEntryLists.addAll(userDTOa12)
                backFlowEntryLists.addAll(userDTOa13)
                backFlowEntryLists.addAll(userDTOa14)
                backFlowEntryLists.addAll(userDTOa15)
                backFlowEntryLists.addAll(userDTOa16)
                backFlowEntryLists.addAll(userDTOa17)
                backFlowEntryLists.addAll(userDTOa18)
                backFlowEntryLists.addAll(userDTOa19)
                backFlowEntryLists.addAll(userDTOa20)*/
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
         /*   val userDTOb1 = setUpBaseList.stream()
                .map<Entry> { user: SetUpBean ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat()/10, user)
                }.collect(Collectors.toList<Entry>())
            val userDTOb2 = setUpBaseList.stream()
                .map<Entry> { user: SetUpBean ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat()/10, user)
                }.collect(Collectors.toList<Entry>())
            val userDTOb3 = setUpBaseList.stream()
                .map<Entry> { user: SetUpBean ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat()/10, user)
                }.collect(Collectors.toList<Entry>())
            val userDTOb4 = setUpBaseList.stream()
                .map<Entry> { user: SetUpBean ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat()/10, user)
                }.collect(Collectors.toList<Entry>())
            val userDTOb5 = setUpBaseList.stream()
                .map<Entry> { user: SetUpBean ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat()/10, user)
                }.collect(Collectors.toList<Entry>())
            val userDTOb6 = setUpBaseList.stream()
                .map<Entry> { user: SetUpBean ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat()/10, user)
                }.collect(Collectors.toList<Entry>())
            val userDTOb7 = setUpBaseList.stream()
                .map<Entry> { user: SetUpBean ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat()/10, user)
                }.collect(Collectors.toList<Entry>())
            val userDTOb8 = setUpBaseList.stream()
                .map<Entry> { user: SetUpBean ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat()/10, user)
                }.collect(Collectors.toList<Entry>())
            val userDTOb9 = setUpBaseList.stream()
                .map<Entry> { user: SetUpBean ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat()/10, user)
                }.collect(Collectors.toList<Entry>())
            val userDTOb10 = setUpBaseList.stream()
                .map<Entry> { user: SetUpBean ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat()/10, user)
                }.collect(Collectors.toList<Entry>())
            val userDTOb11 = setUpBaseList.stream()
                .map<Entry> { user: SetUpBean ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat()/10, user)
                }.collect(Collectors.toList<Entry>())
            val userDTOb12 = setUpBaseList.stream()
                .map<Entry> { user: SetUpBean ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat()/10, user)
                }.collect(Collectors.toList<Entry>())
            val userDTOb13 = setUpBaseList.stream()
                .map<Entry> { user: SetUpBean ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat()/10, user)
                }.collect(Collectors.toList<Entry>())
            val userDTOb14 = setUpBaseList.stream()
                .map<Entry> { user: SetUpBean ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat()/10, user)
                }.collect(Collectors.toList<Entry>())
            val userDTOb15 = setUpBaseList.stream()
                .map<Entry> { user: SetUpBean ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat(), user)
                }.collect(Collectors.toList<Entry>())
            val userDTOb16 = setUpBaseList.stream()
                .map<Entry> { user: SetUpBean ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat(), user)
                }.collect(Collectors.toList<Entry>())
            val userDTOb17 = setUpBaseList.stream()
                .map<Entry> { user: SetUpBean ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat(), user)
                }.collect(Collectors.toList<Entry>())
            val userDTOb18 = setUpBaseList.stream()
                .map<Entry> { user: SetUpBean ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat(), user)
                }.collect(Collectors.toList<Entry>())
            val userDTOb19= setUpBaseList.stream()
                .map<Entry> { user: SetUpBean ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat(), user)
                }.collect(Collectors.toList<Entry>())
            val userDTOb20= setUpBaseList.stream()
                .map<Entry> { user: SetUpBean ->
                    a++
                    Entry(a.toFloat(), user.temperature.toFloat(), user)
                }.collect(Collectors.toList<Entry>())*/
            if (userDTOa!=null){
                setUpBeanEntryLists.addAll(userDTOb)
          /*      setUpBeanEntryLists.addAll(userDTOb1)
                setUpBeanEntryLists.addAll(userDTOb2)
                setUpBeanEntryLists.addAll(userDTOb3)
                setUpBeanEntryLists.addAll(userDTOb4)
                setUpBeanEntryLists.addAll(userDTOb5)
                setUpBeanEntryLists.addAll(userDTOb6)
                setUpBeanEntryLists.addAll(userDTOb7)
                setUpBeanEntryLists.addAll(userDTOb8)
                setUpBeanEntryLists.addAll(userDTOb9)
                setUpBeanEntryLists.addAll(userDTOb10)
                setUpBeanEntryLists.addAll(userDTOb11)
                setUpBeanEntryLists.addAll(userDTOb12)
                setUpBeanEntryLists.addAll(userDTOb13)
                setUpBeanEntryLists.addAll(userDTOb14)
                setUpBeanEntryLists.addAll(userDTOb15)
                setUpBeanEntryLists.addAll(userDTOb16)
                setUpBeanEntryLists.addAll(userDTOb17)
                setUpBeanEntryLists.addAll(userDTOb18)
                setUpBeanEntryLists.addAll(userDTOb19)
                setUpBeanEntryLists.addAll(userDTOb20)*/
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

    suspend  fun getDataTotal(startTime: Date, endTime: Date): Int {
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

    /**
     * x轴数据处理
     *
     * @param valueType 数据类型
     * @return x轴数据
     */
    private fun xValuesProcess(valueType: Int): Array<String?> {


        return arrayOf()
    }

}