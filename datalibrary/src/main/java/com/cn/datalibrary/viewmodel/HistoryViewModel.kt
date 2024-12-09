package com.cn.datalibrary.viewmodel

import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.view.TimePickerView
import com.cn.datalibrary.R
import com.cn.datalibrary.adapter.HistoryAdapter
import com.cn.datalibrary.adapter.HistoryAdapterBg
import com.cn.datalibrary.base.DataHistoryBase
import com.cn.datalibrary.model.HistoryVideoModel
import com.hjq.shape.layout.ShapeLinearLayout
import com.ijcsj.common_library.bean.HistoryBase
import com.ijcsj.common_library.command.BindingAction
import com.ijcsj.common_library.command.BindingCommand
import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.util.DateUtil
import com.ijcsj.common_library.viewadapter.view.ViewAdapter
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel
import com.ijcsj.ui_library.utils.ToastUtils
import com.jakewharton.rxbinding4.view.clicks
import com.orhanobut.logger.Logger
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.Date
import java.util.concurrent.TimeUnit


class HistoryViewModel  (override val model: HistoryVideoModel,var adapter: HistoryAdapter,var adapterBg: HistoryAdapterBg,var dataHistoryBase: DataHistoryBase) : MvmBaseViewModel<IBaseView, HistoryVideoModel>() {

    private val _historyBaseList = MutableLiveData<ObservableList<HistoryBase>>()
    val historyBaseList: LiveData<ObservableList<HistoryBase>> get() = _historyBaseList

    private val _historyBaseBgList = MutableLiveData<ObservableList<HistoryBase>>()
    val historyBaseBgList: LiveData<ObservableList<HistoryBase>> get() = _historyBaseBgList
    public var currentPage:Int=1
    var startTime:Date=DateUtil.parseTime("2000-01-01  12:23:34","yyyy-MM-dd  HH:mm:ss")
    var endTime:Date=Calendar.getInstance().time
    public override fun initModel() {
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
        }) {
            modifyPage(startTime, endTime)
            _historyBaseList.postValue(model.initData(currentPage, startTime, endTime))
        }

        _historyBaseBgList.postValue(model.initDataBg())
    }

    public fun dataC(){
        if (dataHistoryBase.startTime=="请选择开始日期"||dataHistoryBase.endTime=="请选择开始日期"){
            endTime=Calendar.getInstance().time
            viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            }) {
                modifyPage(startTime, endTime)
                _historyBaseList.postValue(model.initData(currentPage, startTime, endTime))
            }
        }else{
            viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            }) {
                modifyPage(startTime, endTime)
                _historyBaseList.postValue(model.initData(currentPage, startTime, endTime))
            }
        }

    }


    var startTimePicker: TimePickerView?=null

    /*  var onClickStartTime=  BindingCommand<BindingAction>{
     }
    var onClickEndTime=  BindingCommand<BindingAction>{

     }*/
   fun onClickStartTime(it: View){
       //获取当前时间
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
           dataHistoryBase.startTime=DateUtil.formatTime(date,"YYYY-MM-dd")
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
            dataHistoryBase.endTime=DateUtil.formatTime(date,"YYYY-MM-dd")
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
            currentPage=1
            viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            }) {
                startTime=DateUtil.parseTime(dataHistoryBase.startTime,"yyyy-MM-dd")
                endTime=Date( dataHistoryBase.endTimeData.time+1000*60*60*23)

                modifyPage(dataHistoryBase.startTimeData
                    , dataHistoryBase.endTimeData)
                _historyBaseList.postValue(model.initData(
                    currentPage, dataHistoryBase.startTimeData
                    , dataHistoryBase.endTimeData))
            }
        }else{
            ToastUtils.show(view.context,"请选择开始时间与结束时间")
        }
    }

/*    var onClickQuery=  BindingCommand<BindingAction>{

    }*/



    var onClickLeft=  BindingCommand<BindingAction>{
        currentPage--
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
        }) {
            modifyPage(startTime, endTime)
            _historyBaseList.postValue(model.initData(currentPage, startTime, endTime))
        }
    }


    var onClickRight=  BindingCommand<BindingAction>{
        currentPage++
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
        }) {
            modifyPage(startTime, endTime)
            _historyBaseList.postValue(model.initData(currentPage, startTime, endTime))
        }
    }

    suspend  fun modifyPage(startTime: Date,  endTime:Date) {
        return   withContext(Dispatchers.IO) {

            var total=  model. getDataTotal(startTime,endTime)
            if (total<=0){
                dataHistoryBase.isRightProhibit=false
                dataHistoryBase.currentPage="第 0 页"
            }else{
                dataHistoryBase.currentPage="第 ${currentPage} 页"
                if ((currentPage)==total){
                    dataHistoryBase.isRightProhibit=false
                }else{
                    dataHistoryBase.isRightProhibit=true
                }
            }
            dataHistoryBase.commonPage="共 $total 页"

            if (currentPage<=1){
                dataHistoryBase.isLeftProhibit=false
            }else{
                dataHistoryBase.isLeftProhibit=true
            }
        }
    }
    companion object {
        @JvmStatic
        @BindingAdapter(value = ["left_right_button", "type_button"], requireAll = false)
        fun onShapeLinearLayout(shapeLinearLayout: View, dataHistoryBase: Boolean,type_button: Int) {
            var imageView=
                if (type_button==0){
                    shapeLinearLayout.findViewById<ImageView>(R.id.img_left)
                }else{
                    shapeLinearLayout.findViewById<ImageView>(R.id.img_right)
                }
            var textView=
                if (type_button==0){
                    shapeLinearLayout.findViewById<TextView>(R.id.tv_left)
                }else{
                    shapeLinearLayout.findViewById<TextView>(R.id.tv_right)
                }
            if (shapeLinearLayout.id==R.id.sl_right||shapeLinearLayout.id==R.id.sl_left){
                shapeLinearLayout.isEnabled=dataHistoryBase
            }
            if (dataHistoryBase){
                textView.setTextColor(shapeLinearLayout. context.getColor(R.color.button_text_on))
                imageView.setColorFilter(shapeLinearLayout.context.getColor(R.color.button_text_on), PorterDuff.Mode.SRC_IN)
            }else{
                textView.setTextColor( shapeLinearLayout.context.getColor(R.color.button_text_off))
                imageView.setColorFilter(shapeLinearLayout.context.getColor(R.color.button_text_off), PorterDuff.Mode.SRC_IN)
            }

        }

    }
}