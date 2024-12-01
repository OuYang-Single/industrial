package com.cn.setuplibrary.viewmodel

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.bigkoo.pickerview.builder.TimePickerBuilder
import com.bigkoo.pickerview.view.TimePickerView
import com.cn.setuplibrary.adapter.HistoryAdapterBgs
import com.cn.setuplibrary.adapter.OperationAdapter
import com.cn.setuplibrary.bean.DataBase
import com.cn.setuplibrary.model.OperationLogModel
import com.cn.setuplibrary.popup.CustomPartShadowPopupView
import com.ijcsj.common_library.bean.DatasBase
import com.ijcsj.common_library.bean.HistoryBase
import com.ijcsj.common_library.command.BindingAction
import com.ijcsj.common_library.command.BindingCommand
import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.util.DateUtil
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel
import com.ijcsj.stUplibrary.R
import com.ijcsj.ui_library.utils.ToastUtils
import com.kongzue.dialogx.dialogs.WaitDialog
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.enums.PopupAnimation
import com.orhanobut.logger.Logger
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.Date

class OperationLogViewModel  (override val model: OperationLogModel,var dataHistoryBase: DataBase,var adapter: OperationAdapter,var adapterBg: HistoryAdapterBgs) : MvmBaseViewModel<IBaseView, OperationLogModel>(){
    private val _historyBaseList = MutableLiveData<ObservableList<DatasBase>>()
    val historyBaseList: LiveData<ObservableList<DatasBase>> get() = _historyBaseList
    private val _historyBaseBgList = MutableLiveData<ObservableList<HistoryBase>>()
    val historyBaseBgList: LiveData<ObservableList<HistoryBase>> get() = _historyBaseBgList
    var startTimePicker: TimePickerView?=null
    private var popupView: CustomPartShadowPopupView? = null
    var attachPopup: BasePopupView? = null
    var  int: Int=0;
    var dBoolean:Int =0
    var boolean: Boolean=false;
    var canId:String="";
    public override fun initModel() {
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
        }) {
            modifyPage(int,boolean,canId)
            _historyBaseList.postValue(model.initData(currentPage, int,boolean,canId))
        }
        _historyBaseBgList.postValue(model.initDataBg())
    }
    public var currentPage:Int=1
    var startTime:Date=DateUtil.parseTime("2000-01-01  12:23:34","yyyy-MM-dd  HH:mm:ss")
    var endTime:Date= Calendar.getInstance().time
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
                startTime= DateUtil.parseTime(dataHistoryBase.startTime,"yyyy-MM-dd")
                endTime= Date( dataHistoryBase.endTimeData.time+1000*60*60*23)
                modifyPage(int,boolean,canId)
                _historyBaseList.postValue(model.initData(
                    currentPage, int,boolean,canId))
            }
        }else{
            ToastUtils.show(view.context,"请选择开始时间与结束时间")
        }
    }

    var onClickSource=  BindingCommand<BindingAction>{
        attachPopup = XPopup.Builder(it.context)
            .atView(it)
            .dismissOnTouchOutside(true)
            .isViewMode(true) //开启View实现
            .isTouchThrough(true)
            .hasShadowBg(false)
            .offsetX(-45)
            .positionByWindowCenter(true)
            .popupAnimation(PopupAnimation.ScaleAlphaFromCenter)
            .asAttachList(
                arrayOf<String>("来源", "发送", "接收"), null
            ) { position, text ->
                it as TextView
                it.text=text
                dBoolean=position;
                boolean = if (position==1){
                    false
                }else{
                    true
                }
                if (dBoolean!=0){
                    if (canId==""){
                        int=1;
                    }else{
                        int=3;
                    }
                }else{
                    if (canId==""){
                        int=0;
                    }else{
                        int=2;
                    }
                }
                WaitDialog.show("数据加载中...");
                viewModelScope.launch(CoroutineExceptionHandler { _, e ->
                }) {
                    modifyPage(int,boolean,canId)
                    _historyBaseList.postValue(model.initData(currentPage, int,boolean,canId))
                }
            }
        attachPopup?.show()
    }
    @SuppressLint("SetTextI18n")
    var onClickSources=  BindingCommand<BindingAction>{
    var list=    arrayOf<Int>(0, 0x99, 0x100, 0x101, 0x102, 0x103, 0x104, 0x105, 0x106,0x107, 0x108,0x109)
        attachPopup = XPopup.Builder(it.context)
            .atView(it)
            .dismissOnTouchOutside(true)
            .isViewMode(true) //开启View实现
            .isTouchThrough(true)
            .hasShadowBg(false)
            .offsetX(-45)
            .positionByWindowCenter(true)
            .popupAnimation(PopupAnimation.ScaleAlphaFromCenter)
            .asAttachList(
                arrayOf<String>("CanId", "099", "100", "101", "102", "103", "104", "105", "106", "107", "108","109"), null
            ) { position, text ->
                WaitDialog.show("数据加载中...");
                it as TextView
                if (position!=0){
                    it.text= "0x$text"
                   canId= Integer.toHexString(list.get(position) and 0x1FFFFFFF)
                    if (dBoolean!=0){
                        int=3;
                    }else{
                        int=2;
                    }
                }else{
                    it.text= text
                    if (dBoolean!=0){
                        int=1;
                    }else{
                        int=0;
                    }
                }
                viewModelScope.launch(CoroutineExceptionHandler { _, e ->
                }) {
                    modifyPage(int,boolean,canId)
                    _historyBaseList.postValue(model.initData(currentPage, int,boolean,canId))
                }
            }
        attachPopup?.show()
    }

    var onClickLeft=  BindingCommand<BindingAction>{
        currentPage--
        WaitDialog.show("数据加载中...");
        Log.w("ouyang", "onClickLeft$currentPage  ${dataHistoryBase.isRightProhibit}  ${dataHistoryBase.isLeftProhibit}" )
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
        }) {
            modifyPage(int,boolean,canId)
            _historyBaseList.postValue(model.initData(currentPage,int,boolean,canId))
        }
    }


    var onClickRight=  BindingCommand<BindingAction>{
        currentPage++
        WaitDialog.show("数据加载中...");
        Log.w("ouyang", "onClickRight$it")
        viewModelScope.launch(CoroutineExceptionHandler { _, e ->
        }) {
            modifyPage(int,boolean,canId)
            _historyBaseList.postValue(model.initData(currentPage, int,boolean,canId))
        }
    }
    suspend  fun modifyPage(int: Int,boolean: Boolean,canId:String) {
        return   withContext(Dispatchers.IO) {
            var total=  model. getDataTotal(int,boolean,canId)
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
        fun hideBottomNavigationBar(window: Window) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                // 在 Android 11（API 级别 30）及以上版本中，可以直接使用 Window 的 setDecorFitsSystemWindows 方法
                window.setDecorFitsSystemWindows(false)
            } else {
                // 在 Android 10（API 级别 29）中，可以尝试使用以下方法
                val decorView: View = window.getDecorView()
                val uiOptions = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // 隐藏导航栏
                        or View.SYSTEM_UI_FLAG_IMMERSIVE // 全屏模式
                        or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_FULLSCREEN) // 隐藏状态栏
                decorView.systemUiVisibility = uiOptions
            }
        }
        @JvmStatic
        @BindingAdapter(value = ["left_right_buttons", "type_buttons"], requireAll = false)
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
                Log.w("ouyang", "onClickLeft$dataHistoryBase  ${shapeLinearLayout.id==R.id.sl_right}  ${shapeLinearLayout.id==R.id.sl_left  }  $type_button" )
                shapeLinearLayout.isEnabled=dataHistoryBase
            }
            if (dataHistoryBase){
                textView.setTextColor(shapeLinearLayout. context.getColor(com.ijcsj.ui_library.R.color.button_text_on))
                imageView.setColorFilter(shapeLinearLayout.context.getColor(com.ijcsj.ui_library.R.color.button_text_on), PorterDuff.Mode.SRC_IN)
            }else{
                textView.setTextColor( shapeLinearLayout.context.getColor(com.ijcsj.ui_library.R.color.button_text_off))
                imageView.setColorFilter(shapeLinearLayout.context.getColor(com.ijcsj.ui_library.R.color.button_text_off), PorterDuff.Mode.SRC_IN)
            }

        }

    }


}