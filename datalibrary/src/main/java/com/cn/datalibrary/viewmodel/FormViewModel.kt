package com.cn.datalibrary.viewmodel

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.view.View
import androidx.databinding.ObservableList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.view.TimePickerView
import com.cn.datalibrary.adapter.DataValueAdapter
import com.cn.datalibrary.base.DataFormBase
import com.cn.datalibrary.base.DataHistoryBase
import com.cn.datalibrary.model.FormViewVideoModel
import com.cn.datalibrary.model.InputVideoModel
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.ijcsj.common_library.bean.TemperatureBase
import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.util.DateUtil
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel
import com.ijcsj.ui_library.utils.ToastUtils
import com.orhanobut.logger.Logger
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

class FormViewModel   (override val model: FormViewVideoModel,var dataHistoryBase: DataFormBase) : MvmBaseViewModel<IBaseView, FormViewVideoModel>() {
    private val _dataTitleList = MutableLiveData<ObservableList<LineDataSet>>()
    val dataTitleList: LiveData<ObservableList<LineDataSet>> get() = _dataTitleList

    private val _dataTitleListd = MutableLiveData<Boolean>()
    val dataTitleListd: LiveData<Boolean> get() = _dataTitleListd
    var startTime:Date=DateUtil.parseTime("2000-01-01 12:23:34","yyyy-MM-dd HH:mm:ss")
    var endTime:Date=Calendar.getInstance().time
    var startTimePicker: TimePickerView?=null
    public override fun initModel() {
        if ( dataHistoryBase.startTimeData!=null&& dataHistoryBase.endTimeData!=null){
            viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            }) {
                var startTime:Date=DateUtil.parseTime(dataHistoryBase.startTime,"yyyy-MM-dd")
                var time=Date( dataHistoryBase.endTimeData.time+1000*60*60*23)
                _dataTitleList.postValue(model.initData(startTime,time))
            }
        }else{
            endTime=Calendar.getInstance().time
            viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            }) {
                _dataTitleList.postValue(model.initData(startTime,endTime))
            }

        }

    }


    fun onClickStartTime(it: View){
        //获取当前时间
        var selectedDate: Calendar = Calendar.getInstance()
        val startDate: Calendar = Calendar.getInstance()
        var endDate: Calendar = Calendar.getInstance()
        if (dataHistoryBase.endTimeData!=null){
            val calendar= Calendar.getInstance()
            calendar.time=dataHistoryBase.endTimeData
            endDate=calendar;
        }

        if (dataHistoryBase.startTimeData!=null){
            val calendar= Calendar.getInstance()
            calendar.time=dataHistoryBase.startTimeData
            selectedDate=calendar;
        }
        startDate.set(2000, 0, 1) //根据需要设计开始时间
        startTimePicker=  TimePickerBuilder(it.context
        ) { date: Date, v: View? ->  //选中事件回调
            dataHistoryBase.startTimeData=date
            dataHistoryBase.startTime= DateUtil.formatTime(date,"YYYY-MM-dd")
        }.setType(booleanArrayOf(true, true, true, false, false, false)) // 默认全部显示
            .setCancelText("取消") //取消按钮文字
            .setSubmitText("确定") //确认按钮文字
            //.setContentSize(18)//滚轮文字大小
            .setTitleSize(20) //标题文字大小
            .setTitleText("开始时间选择") //标题文字
            .setOutSideCancelable(true) //点击屏幕，点在控件外部范围时，是否取消显示
            .isCyclic(false) //是否循环滚动
            .setTitleColor(Color.BLACK) //标题文字颜色
            .setSubmitColor(Color.BLACK) //确定按钮文字颜色
            .setCancelColor(Color.BLACK) //取消按钮文字颜色
            .setTitleBgColor(Color.rgb(255, 255, 255)) //标题背景颜色 Night mode
            .setBgColor(Color.rgb(255, 255, 255)) //滚轮背景颜色 Night mode
            .setDate(selectedDate) // 如果不设置的话，默认是系统时间*/
            .setRangDate(startDate, endDate) //起始终止年月日设定
            .setLabel("年", "月", "日", "时", "分", "秒") //默认设置为年月日时分秒
            .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
            .isDialog(false) //是否显示为对话框样式
            .build()
        startTimePicker?.show();
    }
    fun onClickEndTime(it: View){
        //获取当前时间
        //获取当前时间
        var selectedDate: Calendar = Calendar.getInstance()
        var startDate: Calendar = Calendar.getInstance()
        var endDate: Calendar = Calendar.getInstance()
        if (dataHistoryBase.startTimeData!=null){
            val calendar= Calendar.getInstance()
            calendar.time=dataHistoryBase.startTimeData
            startDate=calendar;
        }

        if (dataHistoryBase.endTimeData!=null){
            val calendar= Calendar.getInstance()
            calendar.time=dataHistoryBase.endTimeData
            selectedDate=calendar;
        }
        startTimePicker=  TimePickerBuilder(it.context
        ) { date: Date, v: View? ->  //选中事件回调
            dataHistoryBase.endTimeData=date
            dataHistoryBase.endTime= DateUtil.formatTime(date,"YYYY-MM-dd")
        }.setType(booleanArrayOf(true, true, true, false, false, false)) // 默认全部显示
            .setCancelText("取消") //取消按钮文字
            .setSubmitText("确定") //确认按钮文字
            //.setContentSize(18)//滚轮文字大小
            .setTitleSize(20) //标题文字大小
            .setTitleText("结束时间选择") //标题文字
            .setOutSideCancelable(true) //点击屏幕，点在控件外部范围时，是否取消显示
            .isCyclic(false) //是否循环滚动
            .setTitleColor(Color.BLACK) //标题文字颜色
            .setSubmitColor(Color.BLACK) //确定按钮文字颜色
            .setCancelColor(Color.BLACK) //取消按钮文字颜色
            .setTitleBgColor(Color.rgb(255, 255, 255)) //标题背景颜色 Night mode
            .setBgColor(Color.rgb(255, 255, 255)) //滚轮背景颜色 Night mode
            .setDate(selectedDate) // 如果不设置的话，默认是系统时间*/
            .setRangDate(startDate, endDate) //起始终止年月日设定
            .setLabel("年", "月", "日", "时", "分", "秒") //默认设置为年月日时分秒
            .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
            .isDialog(false) //是否显示为对话框样式
            .build()
        startTimePicker?.show();
    }

    fun onClickQuery(view: View){
        Logger.w("onClickQuery");
        if ( dataHistoryBase.startTimeData!=null&& dataHistoryBase.endTimeData!=null){
            viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            }) {
                var startTime:Date=DateUtil.parseTime(dataHistoryBase.startTime,"yyyy-MM-dd")
               var time=Date( dataHistoryBase.endTimeData.time+1000*60*60*23)
                _dataTitleList.postValue(model.initData(startTime,time))
            }
        }else{
            ToastUtils.show(view.context,"请选择开始时间与结束时间")
            _dataTitleListd.postValue(true)

        }
    }

}