package com.cn.main_library.viewmodel

import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cn.main_library.R
import com.cn.main_library.adapter.PhoneMainAdapter
import com.cn.main_library.adapter.ProjectAdapter
import com.cn.main_library.base.MainBase
import com.cn.main_library.base.ProjectBase
import com.cn.main_library.model.MainVideoModel
import com.cn.main_library.popup.InputPopup
import com.ijcsj.common_library.bean.CanFrame
import com.ijcsj.common_library.can.Socketcan
import com.ijcsj.common_library.command.BindingAction
import com.ijcsj.common_library.command.BindingCommand
import com.ijcsj.common_library.mmkv.ShuJuMMkV
import com.ijcsj.common_library.ui.IBaseView
import com.ijcsj.common_library.util.Hexs
import com.ijcsj.common_library.util.a
import com.ijcsj.common_library.viewmodel.MvmBaseViewModel
import com.ijcsj.ui_library.utils.AppGlobals
import com.ijcsj.ui_library.widget.dashboardview.view.DashboardView
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.orhanobut.logger.Logger

class MainViewModel (override val model: MainVideoModel,var mainBase: MainBase,var adapter: ProjectAdapter,var phoneMainAdapter: PhoneMainAdapter) : MvmBaseViewModel<IBaseView, MainVideoModel>() {
    private val _projectBaseList = MutableLiveData<ObservableList<ProjectBase>>()
    val projectBaseList: LiveData<ObservableList<ProjectBase>> get() = _projectBaseList
    var projectBaseLists:  ObservableList<ProjectBase>?=null
    var basePopupView: BasePopupView?=null;
    public override fun initModel() {
        AppGlobals.get()?.let {
            if ("com.cn.phoneapp"== it.packageName)  {
                _projectBaseList.postValue( model.initPhoneData())

            }else{
                _projectBaseList.postValue( model.initData())
        }
    }
    }

    fun setCan100Data(it: CanFrame) {
      var data1= ( it.data[0].toFloat()/(10).toFloat()).toInt()
        if (data1>=0){
            mainBase.flow=(it.data[0].toFloat()/(10).toFloat()).toInt()
            mainBase.flowString=(it.data[0].toFloat()/(10).toFloat()).toString()
        }else{
            mainBase.flow=0
            mainBase.flowString=0.0.toString()
        }

        mainBase.pressure= (it.data[7].toFloat()/(10).toFloat()).toInt()
        mainBase.pressureString= (it.data[7].toFloat()/(10).toFloat()).toString()
        mainBase.temperature= ( Hexs.pinJie2ByteToInt(it.data[4],it.data[3]).toFloat()/(10).toFloat()).toInt()
        mainBase.temperatureString=   ( Hexs.pinJie2ByteToInt(it.data[4],it.data[3]).toFloat()/(10).toFloat()).toString()
        projectBaseLists?.let {data->
          var data0=  ( Hexs.pinJie2ByteToInt(it.data[2],it.data[1]).toFloat()/(10).toFloat()).toString()
            if ( data0!=   data[0].value){
                data[0] =ProjectBase( data[0].name, data0, data[0].unit)
            }
        }
    }

    fun setCan099Data(it: CanFrame, data: ObservableList<ProjectBase>) {
       var data1= (it.data[5].toFloat()/(10).toFloat()).toString()
       var data2= (it.data[4].toFloat()/(10).toFloat()).toString()
       var data3= (it.data[3].toFloat()/(10).toFloat()).toString()
       var data4= (it.data[7].toFloat()/(10).toFloat()).toString()
        if ( data1!=   data[1].value){
            data[1] =ProjectBase( data[1].name, data1, data[1].unit)
        }
        if ( data2!=   data[2].value){
            data[2] =ProjectBase( data[2].name, data2, data[2].unit)
        }
        if ( data3!=   data[3].value){
            data[3] =ProjectBase( data[3].name, data3, data[3].unit)
        }
        if ( data4!=   data[4].value){
            data[4] =ProjectBase( data[4].name, data4, data[4].unit)
        }
    }

    fun setCan102Data(it: CanFrame) {
        var data1=   Hexs.getBitByByte( it.data[0],0)==1
        var data2=    Hexs.getBitByByte( it.data[0],1)==1
        var data3=   Hexs.getBitByByte( it.data[0],2)==1
        var data4=    Hexs.getBitByByte( it.data[0],3)==1
        mainBase.isWaterPump=data1
        mainBase.isHeat=data3
        mainBase.isMoisturizing=data4
        mainBase.isBurial=data2
        var data5=  Hexs. getBitByByte(it.data[1],0,3)
        var stringData=   when(data5){
            0->{
               "待机状态"
            }
            1->{
                "填水状态"
            }
            2->{
                "快速调整状态"
            }
            3->{
                "正常调整状态"
            }
            4->{
                "关机状态"
            }
            5->{
                "排空状态"
            }
            6->{
                "强制冷却状态"
            }
            else -> {
                "--"
            }
        }
        var type=   ShuJuMMkV.getInstances()?.getString(a.WORKING_MODE, 0.toString())
        mainBase.istype=  when(type?.toInt()){
          0-> { 0 }
          1, 2,3,5,6->{ 1 }
          4->{ 2 }else -> {2}
        }


        mainBase.workStatusProcess=stringData
    }
    fun setCan101Data(it: CanFrame) {
        var data1=  Hexs. getBitByByte(it.data[0],0,1)
        var data2=  Hexs. getBitByByte(it.data[0],2,3)
        var data3=  Hexs. getBitByByte(it.data[0],4,5)
        var data4=  Hexs. getBitByByte(it.data[0],6,7)
        var data5=  Hexs. getBitByByte(it.data[1],0,1)
        var data6=  Hexs. getBitByByte(it.data[1],2,3)
        var data7=  Hexs. getBitByByte(it.data[1],4,5)
        var data8=  Hexs. getBitByByte(it.data[1],6,7)
        var data9=  Hexs. getBitByByte(it.data[2],0,1)
        var data10=  Hexs. getBitByByte(it.data[2],2,3)
        var data11=  Hexs. getBitByByte(it.data[2],4,5)
        var data12=  Hexs. getBitByByte(it.data[2],6,7)
        var data13=  Hexs. getBitByByte(it.data[3],0,1)
        var data14=  Hexs. getBitByByte(it.data[3],2)
        var data15=  Hexs. getBitByByte(it.data[3],3)
        var data16=  Hexs. getBitByByte(it.data[3],6,7)
        val data: ObservableList<Int> = ObservableArrayList()
        data.add(data1)
        data.add(data2)
        data.add(data3)
        data.add(data4)
        data.add(data5)
        data.add(data6)
        data.add(data7)
        data.add(data8)
        data.add(data9)
        data.add(data10)
        data.add(data11)
        data.add(data12)
        data.add(data13)
        data.add(data14)
        data.add(data15)
        data.add(data16)
        var valueData1=false;
        var valueData2=false;
        var valueData3=false;
        for (i in 0 until data.size) {
           if (data[i]>=1){
               if (i==4){
                   valueData1=true;
               }
               if (i==15){
                   valueData2=true;
               }
               if (i==6){
                   valueData3=true;
               }
           }
        }


        mainBase.isWarn1=valueData1
        mainBase.isWarn2=valueData2
        mainBase.isWarn3=valueData3
    }

    var onSwitchClick=  BindingCommand<BindingAction>{
        Logger.w("onPasswordClick");
        var bytes2=ByteArray(8)
        when( mainBase.istype){
            0,4->{
                bytes2[0]= a.from10To2sd(1)
            }
            else -> {
                bytes2[0]= a.from10To2sd(0)
            }
        }
        mainBase.istype= bytes2[0].toInt()
        ShuJuMMkV.getInstances()?.putString(a.WORKING_MODE,  bytes2[0].toString())
        var d1=  ShuJuMMkV.getInstances()?.getString(a.SETTING_TEMPERATURE,"1200")
        var d2=  ShuJuMMkV.getInstances()?.getString(a.FILLING_TIME,"4")
        var d3=  ShuJuMMkV.getInstances()?.getString(a.PUMP_ON_TIME,"0")
        var d4=  ShuJuMMkV.getInstances()?.getString(a.IP_ADDRESS,"0")
        var d5=  ShuJuMMkV.getInstances()?.getString(a.COOLING_TEMPERATURE,"60")
        var d6=  ShuJuMMkV.getInstances()?.getString(a.DELAY_PUMP_START_TIME,"10")
        var d11=  Hexs.  hex2LowHighByte(d1!!.toLong())
        bytes2[1]=d11[0]
        bytes2[2]=d11[1]
        bytes2[3]=  (d2!!.toInt() and 0xff).toByte()
        bytes2[4]= (d3!!.toInt()and 0xff).toByte()
        bytes2[5]= (d4!!.toInt()and 0xff ).toByte()
        bytes2[6]= (d5!!.toInt()and 0xff).toByte()
        bytes2[7]= (d6!!.toInt()and 0xff ).toByte()
        var a=  Socketcan.CanWrites(Socketcan.fd,Socketcan.CAN_103,bytes2)
        Logger.w("onPasswordClick  $a   ${Socketcan.fd}");

    }
    var onClickTemperature=  BindingCommand<BindingAction>{
        basePopupView= XPopup.Builder(it.context)
            .enableDrag(false)
            .autoDismiss(false)
            .dismissOnTouchOutside(false)
            .positionByWindowCenter(true)
            .asCustom(InputPopup(it.context,object : InputPopup.OnClickListeners{
                override fun onClick(string: String?) {
                    try {
                        var d111=string?.toFloat()
                        if (d111==null || d111<0|| d111>200){
                            _message.postValue("温度设置超出范围")
                            return
                        }
                        var bytes2=ByteArray(8)
                        var d0=  ShuJuMMkV.getInstances()?.getString(a.WORKING_MODE,  "0")
                        var d2=  ShuJuMMkV.getInstances()?.getString(a.FILLING_TIME,"4")
                        var d3=  ShuJuMMkV.getInstances()?.getString(a.PUMP_ON_TIME,"0")
                        var d4=  ShuJuMMkV.getInstances()?.getString(a.IP_ADDRESS,"0")
                        var d5=  ShuJuMMkV.getInstances()?.getString(a.COOLING_TEMPERATURE,"60")
                        var d6=  ShuJuMMkV.getInstances()?.getString(a.DELAY_PUMP_START_TIME,"10")
                        var d11=  Hexs.  hex2LowHighByte((d111*10).toInt().toLong())
                        bytes2[0]= (d0!!.toInt() and 0xff).toByte()
                        bytes2[1]=d11[0]
                        bytes2[2]=d11[1]
                        bytes2[3]=  (d2!!.toInt() and 0xff).toByte()
                        bytes2[4]= (d3!!.toInt()and 0xff).toByte()
                        bytes2[5]= (d4!!.toInt()and 0xff ).toByte()
                        bytes2[6]= (d5!!.toInt()and 0xff).toByte()
                        bytes2[7]= (d6!!.toInt()and 0xff ).toByte()

                        var a1=  Socketcan.CanWrites(Socketcan.fd,Socketcan.CAN_103,bytes2)
                        if (a1>0){
                            ShuJuMMkV.getInstances()?.putString(a.SETTING_TEMPERATURE,(d111*10).toInt().toString())
                            mainBase.settingTemperature=(d111.toFloat()).toString()
                        }else{
                            _message.postValue("发送失败，请重试")
                        }
                        Logger.w("onPasswordClick  $a1  ${Socketcan.fd}");
                    }catch (e:Exception){
                        _message.postValue("请输入正确温度")
                    }
                }

            })).show()



    }
    companion object {
    @JvmStatic
    @BindingAdapter(value = ["setPercent"])
    fun DashboardView.setPercent(circleUrl: Int) {

       this.setPercent(circleUrl)
      }

        @JvmStatic
        @BindingAdapter(value = ["type"])
        fun ImageView.setType(circleUrl: Int) {
            Logger.w("setType  $circleUrl");
            var stringData=   when(circleUrl){
                0->{
                    R.drawable.ic_switch_o//橙色
                }
                4->{
                    R.drawable.ic_switch//
                }

                else -> {
                    R.drawable.ic_switch_on
                }
            }
           this.setImageResource(stringData)
        }
        @JvmStatic
        @BindingAdapter(value = ["tv_color"])
        fun TextView.txt(circleUrl: Boolean) {
            Logger.w("setType  $circleUrl");
          if (circleUrl){
             setTextColor(Color.parseColor("#D81E06"))
          }else{
              setTextColor(Color.parseColor("#ffaff178"))
          }
        }
    }
}