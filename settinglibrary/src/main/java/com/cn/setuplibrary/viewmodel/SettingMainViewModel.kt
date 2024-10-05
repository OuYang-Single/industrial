package com.cn.setuplibrary.viewmodel

import android.text.TextUtils
import androidx.databinding.ObservableList
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.cn.setuplibrary.adapter.DataTitleAdapter
import com.cn.setuplibrary.model.SettingMainModel
import com.cn.setuplibrary.popup.ConfirmPopup
import com.github.mikephil.charting.data.LineDataSet
import com.ijcsj.common_library.bean.DataTitle
import com.ijcsj.common_library.can.Socketcan
import com.ijcsj.common_library.command.BindingAction
import com.ijcsj.common_library.command.BindingCommand
import com.ijcsj.common_library.mmkv.ShuJuMMkV
import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.util.Constant
import com.ijcsj.common_library.util.DateUtil
import com.ijcsj.common_library.util.ExcelChart
import com.ijcsj.common_library.util.Hexs
import com.ijcsj.common_library.util.LiveDataBus
import com.ijcsj.common_library.util.a
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel
import com.kongzue.dialogx.dialogs.WaitDialog
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.orhanobut.logger.Logger
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar
import java.util.Date


class SettingMainViewModel (override val model: SettingMainModel,var adapter: DataTitleAdapter) : MvmBaseViewModel<IBaseView, SettingMainModel>(){

    var basePopupView: BasePopupView?=null;
    private val _dataTitleList = MutableLiveData<ObservableList<DataTitle>>()
    val dataTitleList: LiveData<ObservableList<DataTitle>> get() = _dataTitleList

    private val _dataFragmentList = MutableLiveData<ArrayList<Fragment>>()
    val dataFragmentList: LiveData<ArrayList<Fragment>> get() = _dataFragmentList

    private val _dataTitle = MutableLiveData<Boolean>()
    val dataTitle: LiveData<Boolean> get() = _dataTitle

    var startTime: Date = DateUtil.parseTime("2000-01-01 12:23:34","yyyy-MM-dd HH:mm:ss")
    var endTime: Date = Calendar.getInstance().time
    private val _currentItem = MutableLiveData<Int>()
    val currentItem: LiveData<Int> get() = _currentItem
    public override fun initModel() {
        _dataTitleList.postValue( model.initData())
     /*   viewModelScope.launch(CoroutineExceptionHandler { _, e ->
        }) {
            _dataTitle.postValue(model.initDatas(startTime,endTime))
        }*/
        _dataFragmentList.postValue( model.initDataFragment())
        adapter.setOnItemClickListener { v, vdb, position ->
            var data: DataTitle?=null
            var positions=0;
            for (i in 0 until adapter.datas.size) {
                adapter.datas[i] as DataTitle
                if (adapter.datas[i] .isSelect){
                    data=adapter.datas[i]
                    positions=i;
                }
            }
            data?.isSelect=false;
            data?.let {
                (adapter.datas as ObservableList<DataTitle>)[positions] = it
            }
            var dataPositon=  (adapter.datas as ObservableList<DataTitle>)[position]
            dataPositon.isSelect=true
            (adapter.datas as ObservableList<DataTitle>)[position] = dataPositon
            _currentItem.postValue(position)

        }
    }

    var onClickDownloadCourseInfo=  BindingCommand<BindingAction>{


    }
    public fun getDeviceList(){

    }
    var onClickBut1 =  BindingCommand<BindingAction>{
        if (it.tag=="1"){
            WaitDialog.show("表格生成中...");
            var endTime: Date = Calendar.getInstance().time
            viewModelScope.launch(CoroutineExceptionHandler { _, e ->
            }) {
               var data= model.initDatas(startTime,endTime)
                val excelChart = ExcelChart()
              var a=  excelChart.CreateGraph(
                    "/sdcard/2012halinfo",
                    data
                )
                _dataTitle.postValue(a)

            }
        }else{
            basePopupView= XPopup.Builder(it.context)
                .enableDrag(false)
                .autoDismiss(false)
                .dismissOnTouchOutside(false)
                .positionByWindowCenter(true)
                .asCustom(ConfirmPopup(it.context,"是否参数初始化？",object : ConfirmPopup.OnClickListeners{
                    override fun onClick(string: String?) {
                        CAN_103()
                        CAN_105()
                        CAN_108()
                        LiveDataBus.get().with("onClickBut1", Boolean::class.java ).postValue(true)
                    }
                })).show()
        }
    }
    var onClickBut2 =  BindingCommand<BindingAction>{
        basePopupView= XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(ConfirmPopup(it.context,"是否清除历史故障？",object : ConfirmPopup.OnClickListeners{
                override fun onClick(string: String?) {
                    viewModelScope.launch(CoroutineExceptionHandler { _, e ->
                    }) {
                        withContext(Dispatchers.IO){
                            model.dataDeletes()
                        }
                    }
                }

            })).show()

    }

    fun CAN_103():Boolean{
        var bytes2=ByteArray(8)
        var d0=  ShuJuMMkV.getInstances()?.getString(a.WORKING_MODE,"0")
        var d1=  1200
        var d2=  4
        var d3= 0
        var d4=  0
        var d5= 60
        var d6= 10
        var d11=  Hexs.  hex2LowHighByte(d1!!.toLong())
        bytes2[0]= (d0!!.toInt()and 0xff).toByte()
        bytes2[1]=d11[0]
        bytes2[2]=d11[1]
        bytes2[3]=  (d2!!.toInt()and 0xff).toByte()
        bytes2[4]= (d3!!.toInt()and 0xff).toByte()
        bytes2[5]= (d4!!.toInt() and 0xff).toByte()
        bytes2[6]= (d5!!.toInt()and 0xff).toByte()
        bytes2[7]= (d6!!.toInt() and 0xff ).toByte()
        var ta=  Socketcan.CanWrite(Socketcan.fd,Socketcan.CAN_103,bytes2)
        ShuJuMMkV.getInstances()?.putString(a.SETTING_TEMPERATURE,"1200")
        ShuJuMMkV.getInstances()?.putString(a.FILLING_TIME,"4")
        ShuJuMMkV.getInstances()?.putString(a.PUMP_ON_TIME,"0")
        ShuJuMMkV.getInstances()?.putString(a.IP_ADDRESS,"0")
        ShuJuMMkV.getInstances()?.putString(a.COOLING_TEMPERATURE,"60")
        ShuJuMMkV.getInstances()?.putString(a.DELAY_PUMP_START_TIME,"10")
        if (ta>0){
            return true
        }else{
            return false
        }
    }

    fun CAN_105():Boolean{
        var bytes2=ByteArray(8)
        var d0=  0
        var d1=  50
        var d2=  50
        var d3= 50
        var d4= 20
        var d5=  120
        var d6=  120
        bytes2[0]=(d0!!.toInt()and 0xff).toByte()
        bytes2[1]=(d1!!.toInt()and 0xff).toByte()
        bytes2[2]=(d2!!.toInt()and 0xff).toByte()
        bytes2[3]=  (d3!!.toInt()and 0xff).toByte()
        bytes2[4]= (d4!!.toInt()and 0xff).toByte()
        bytes2[5]= (d5!!.toInt() and 0xff).toByte()
        bytes2[6]= (d6!!.toInt()and 0xff).toByte()
        ShuJuMMkV.getInstances()?.putString(a.TEMP_MODE,"0")
        ShuJuMMkV.getInstances()?.putString(a.TEMPERATURE_DIFFERENCE,"50")
        ShuJuMMkV.getInstances()?.putString(a.HIGH_DEVIATION,"50")
        ShuJuMMkV.getInstances()?.putString(a.LOW_DEVIATION,"50")
        ShuJuMMkV.getInstances()?.putString(a.TEMPERATURE_DEVIATION_TIME,"20")
        ShuJuMMkV.getInstances()?.putString(a.HEATING_TIMEOUT,"120")
        ShuJuMMkV.getInstances()?.putString(a.COOLING_TIMEOUT,"120")
        var ta=  Socketcan.CanWrite(Socketcan.fd,Socketcan.CAN_105,bytes2)
        Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
        if (ta>0){
          return true;
        }else{
          return false;
        }
    }

    fun CAN_108():Boolean{
        var bytes2=ByteArray(8)
        var d0= 100
        var d1=  1
        var d2=  10
        var d3= 0
        var d4= 0
        bytes2[0]= (d0!!.toInt() and 0xff).toByte()
        bytes2[1]= (d1!!.toInt() and 0xff).toByte()
        bytes2[2]= (d2!!.toInt() and 0xff).toByte()
        bytes2[3]= (d3!!.toInt() and 0xff).toByte()
        bytes2[4]= (d4!!.toInt() and 0xff).toByte()
        ShuJuMMkV.getInstances()?.putString(a.PID_P,"100")
        ShuJuMMkV.getInstances()?.putString(a.PID_I,"1")
        ShuJuMMkV.getInstances()?.putString(a.PID_D,"10")
        ShuJuMMkV.getInstances()?.putString(a.COAL_COMPENSATION,"0")
        ShuJuMMkV.getInstances()?.putString(a.COAL_RETURN_COMPENSATION,"0")
        var a1=  Socketcan.CanWrite(Socketcan.fd, Socketcan.CAN_108,bytes2)
        if (a1>0){
            return true
        }else{
           return false
        }
    }

    fun CAN_106():Boolean{
        var bytes2=ByteArray(8)
        var d0=  50
        var d1=  50
        var d2= 130
        var d3= 20
        var d4=  10
        var d5= 25
        var d6= 5
        bytes2[0]=(d0!!.toInt()and 0xff).toByte()
        bytes2[1]=(d1!!.toInt()and 0xff).toByte()
        bytes2[2]=(d2!!.toInt()and 0xff).toByte()
        bytes2[3]=  (d3!!.toInt()and 0xff).toByte()
        bytes2[4]= (d4!!.toInt()and 0xff).toByte()
        bytes2[5]= (d5!!.toInt() and 0xff).toByte()
        bytes2[6]= (d6!!.toInt()and 0xff).toByte()
        ShuJuMMkV.getInstances()?.putString(a.EXHAUST_PRESSURE,"50")
        ShuJuMMkV.getInstances()?.putString(a.RETURN_PRESSURE_DIFFERENCE,"50")
        ShuJuMMkV.getInstances()?.putString(a.HIGH_PRESSURE_DEVIATION,"130")
        ShuJuMMkV.getInstances()?.putString(a.LOW_PRESSURE_DEVIATION,"20")
        ShuJuMMkV.getInstances()?.putString(a.MINIMUM_INLET_PRESSURE,"10")
        ShuJuMMkV.getInstances()?.putString(a.MAXIMUM_RETURN_WATER_PRESSURE,"25")
        ShuJuMMkV.getInstances()?.putString(a.MINIMUM_PUMP_PRESSURE,"5")
        var ta=  Socketcan.CanWrite(Socketcan.fd,Socketcan.CAN_106,bytes2)
        Logger.w("onPasswordClick  $ta   ${Socketcan.fd}");
        if (ta>0){
            return true
        }else{
            return false
        }
    }

    fun sbSettingSizeSystem(): Boolean {
        return  ShuJuMMkV.instance?.getBoolean(Constant.SB_SETTING_SIZE_SYSTEM) == true
    }

    fun setSbSettingSizeSystem(boolean: Boolean) {
          ShuJuMMkV.instance?.putBoolean(Constant.SB_SETTING_SIZE_SYSTEM,boolean)

    }

    fun sbSettingCustomizedPlayback(): Boolean {
        return  ShuJuMMkV.instance?.getBoolean(Constant.SB_SETTING_CUSTOMIZED_PLAYBACK) == true
    }

    fun setSbSettingCustomizedPlayback(checked: Boolean) {
        ShuJuMMkV.instance?.putBoolean(Constant.SB_SETTING_CUSTOMIZED_PLAYBACK,checked)
    }

    fun sbSettingLandscapeScreenPlayback(): Boolean {
        return  ShuJuMMkV.instance?.getBoolean(Constant.SB_SETTING_LANDSCAPE_SCREEN_PLAYBACK) == true
    }

    fun setSbSettingLandscapeScreenPlayback(checked: Boolean) {
        ShuJuMMkV.instance?.putBoolean(Constant.SB_SETTING_LANDSCAPE_SCREEN_PLAYBACK,checked)
    }


    fun sbSettingInduction(): Boolean {
        return  ShuJuMMkV.instance?.getBoolean(Constant.SB_SETTING_INDUCTION) == true
    }

    fun setSbSettingInduction(checked: Boolean) {
        ShuJuMMkV.instance?.putBoolean(Constant.SB_SETTING_INDUCTION,checked)
    }

    fun sbSettingBarrageMemory(): Boolean {
        return  ShuJuMMkV.instance?.getBoolean(Constant.SB_SETTING_BARRAGE_MEMORY) == true
    }

    fun setSbSettingBarrageMemory(checked: Boolean) {
        ShuJuMMkV.instance?.putBoolean(Constant.SB_SETTING_BARRAGE_MEMORY,checked)
    }

    fun sbSettingBarrageOperation() : Boolean {
        return  ShuJuMMkV.instance?.getBoolean(Constant.SB_SETTING_BARRAGE_OPERATION) == true
    }

    fun setSbSettingBarrageOperation(checked: Boolean) {
        ShuJuMMkV.instance?.putBoolean(Constant.SB_SETTING_BARRAGE_OPERATION,checked)
    }

    fun sbSettingSmallScreen(): Boolean {
        return  ShuJuMMkV.instance?.getBoolean(Constant.sb_setting_small_screen) == true
    }

    fun setSbSettingSmallScreen(checked: Boolean) {
        ShuJuMMkV.instance?.putBoolean(Constant.sb_setting_small_screen,checked)
    }
}